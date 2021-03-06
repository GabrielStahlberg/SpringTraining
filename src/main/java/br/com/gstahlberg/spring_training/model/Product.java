package br.com.gstahlberg.spring_training.model;

import br.com.gstahlberg.spring_training.model.enumeration.EnumCategory;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String brand;

    @NotNull
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumCategory category;
}
