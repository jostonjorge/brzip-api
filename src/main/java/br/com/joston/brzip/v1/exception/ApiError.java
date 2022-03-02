package br.com.joston.brzip.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private String code = "";
    private String message = "";
    private String description = "";
    private LocalDateTime time;

    public ApiError(String code, String message,String description){
        this.code = code;
        this.message = message;
        this.description = description;
        this.time = LocalDateTime.now();
    }

    public ApiError(String message,String description){
        this.message = message;
        this.description = description;
        this.time = LocalDateTime.now();
    }

    public ApiError(String message){
        this.message = message;
        this.time = LocalDateTime.now();
    }
}
