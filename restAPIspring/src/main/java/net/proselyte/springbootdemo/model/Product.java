package net.proselyte.springbootdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "product", schema = "public")
public class Product {
    @Setter
    @Getter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "place")
    @NotEmpty(message = "Место пустое")
    private String place;

    @Getter
    @Setter
    @Column(name = "name")
    @NotEmpty(message = "Имя пустое")
    private String name;

    @Getter
    @Setter
    @Column(name = "datebeg")
    //@NotEmpty(message = "Нет даты начала")
    private Timestamp datebeg;

    @Getter
    @Setter
    @Column(name = "dateend")
    //@NotEmpty(message = "Нет даты конца")
    private Timestamp dateend;

    public Product(){

    }
    public Product(String place,String name) {
        this.place = place;
        this.name = name;
    }
}
