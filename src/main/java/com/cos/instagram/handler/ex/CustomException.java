package com.cos.instagram.handler.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException{

    private static final long serialVersionUID=1L;

    public CustomException(String message) {
        super(message);
    }
}
