package net.proselyte.springbootdemo.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import java.sql.Timestamp;

public class ProductDTO {
    @Getter
    @Setter
    @NotEmpty(message = "Место пустое")
    private String place;

    @Getter
    @Setter
    @NotEmpty(message = "Имя пустое")
    private String name;


    @Getter
    @Setter
    private Timestamp dateend;
}
