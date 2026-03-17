package com.prueba.tecnica.mvp.handler.Error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseApiError {
    private int status;
    private String method;
    private String path;
    private String message;
    private String cause;
    private LocalDateTime timestamp;
}
