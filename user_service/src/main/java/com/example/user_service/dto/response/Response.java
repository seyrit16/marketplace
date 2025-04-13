package com.example.user_service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Response<T> {
    private Boolean success;
    private Long status;
    protected T data;
    private String errorCode;
    private String errorMessage;

    public static Response<?> SUCCESS = new Response<>(true, 200L, null, null, null);

    public static <T> Response<T> of(T data) {
        return new Response<>(true, 0L, data, null, null);
    }

    public boolean isSuccess() {
        return getSuccess();
    }

    public boolean isFailure() {
        return !getSuccess();
    }

    public static <T> Response<T> of(T data, Long status) {
        return new Response<>(true, status, data, null, null);
    }
}
