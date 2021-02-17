package br.com.gstahlberg.spring_training.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorFormDTO {
    private String field;
    private String error;
}
