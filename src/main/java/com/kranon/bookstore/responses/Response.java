package com.kranon.bookstore.responses;

import lombok.Data;

@Data
public class Response<T> {
    private T data;
    private boolean succeeded;
    private String message;

    public Response() {}

    public Response(T data) {
        this.data = data;
        this.succeeded = true;
        this.message = "";
    }

    public Response(String message, boolean succeeded) {
        this.succeeded = succeeded;
        this.message = message;
    }
}
