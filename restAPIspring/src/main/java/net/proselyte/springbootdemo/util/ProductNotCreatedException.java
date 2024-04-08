package net.proselyte.springbootdemo.util;

public class ProductNotCreatedException extends RuntimeException {
    public ProductNotCreatedException(String msg) {
        super(msg);
    }
}
