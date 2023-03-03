package com.devo.product.web.error;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {

    @NotNull
    private Date timestamp;

    @NotNull
    private int status;

    private String error;

    private String message;

    private String path;

}
