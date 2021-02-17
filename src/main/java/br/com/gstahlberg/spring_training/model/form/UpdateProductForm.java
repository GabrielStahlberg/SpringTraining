package br.com.gstahlberg.spring_training.model.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateProductForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Double price;
}
