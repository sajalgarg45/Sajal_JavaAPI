package com.sajal.javaApi.dto;

public class ApiResponse<T> {

    private boolean is_success;
    private String official_email;
    private T data;
    private String error;

    public ApiResponse(boolean is_success, String official_email, T data) {
        this.is_success = is_success;
        this.official_email = official_email;
        this.data = data;
    }

    public ApiResponse(boolean is_success, String official_email, String error) {
        this.is_success = is_success;
        this.official_email = official_email;
        this.error = error;
    }

    public boolean isIs_success() {
        return is_success;
    }

    public String getOfficial_email() {
        return official_email;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
