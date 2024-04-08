package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.model.Product;
import net.proselyte.springbootdemo.repository.ProductRepository;
import net.proselyte.springbootdemo.util.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional
    public Product saveProduct(Product product){
        enrichProduct(product);
        return productRepository.save(product);
    }



    @Transactional
    public void deleteById(Integer id){
        productRepository.deleteById(id);
    }


    public Product findOne(Integer id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.orElseThrow(ProductNotFoundException::new);
    }

    private void enrichProduct(Product product) {
        product.setDatebeg(Timestamp.valueOf(LocalDateTime.now()));
    }

}
