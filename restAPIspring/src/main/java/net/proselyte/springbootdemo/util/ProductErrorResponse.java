package net.proselyte.springbootdemo.util;

import lombok.Getter;
import lombok.Setter;

public class ProductErrorResponse {
    @Getter
    @Setter
    private String message;
    private long timestamp;

    public ProductErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
