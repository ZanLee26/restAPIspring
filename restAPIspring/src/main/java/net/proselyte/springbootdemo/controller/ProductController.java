package net.proselyte.springbootdemo.controller;

import lombok.RequiredArgsConstructor;
import net.proselyte.springbootdemo.dto.ProductDTO;
import net.proselyte.springbootdemo.model.Product;
import net.proselyte.springbootdemo.service.ProductService;
import net.proselyte.springbootdemo.util.ProductErrorResponse;
import net.proselyte.springbootdemo.util.ProductNotCreatedException;
import net.proselyte.springbootdemo.util.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;


    @GetMapping("/all")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findUser(@PathVariable("id") Integer id) {
        return productService.findOne(id);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            productService.deleteById(id);
        } catch (Exception e) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new ProductNotCreatedException(errorMsg.toString());
        }
        productService.saveProduct(convertToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Product convertToProduct(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Integer id, @RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new ProductNotCreatedException(errorMsg.toString());
        }
        productService.saveProduct(convertToProductUpdate(productDTO, id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Product convertToProductUpdate(ProductDTO productDTO, Integer id) {
        Product product = new Product();

        product.setPlace(productDTO.getPlace());
        product.setName(productDTO.getName());
        product.setDateend(productDTO.getDateend());
        product.setId(id);

        return product;
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e) {
        ProductErrorResponse response = new ProductErrorResponse(
                "Продукт не найден с таким id",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //404
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotCreatedException e) {
        ProductErrorResponse response = new ProductErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}






