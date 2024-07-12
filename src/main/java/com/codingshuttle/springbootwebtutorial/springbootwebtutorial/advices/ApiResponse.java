package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import java.time.LocalDateTime;

public class ApiResponse <T>{
    private T data;
    private ApiError error;
    private LocalDateTime timeStamp;


}
