package com.app.Auth.Utils;

import java.util.List;

public class ApiResponse<T>{
    private String status ;
    private List message ;
    private T data ;

    public ApiResponse(String status, List message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public List getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
