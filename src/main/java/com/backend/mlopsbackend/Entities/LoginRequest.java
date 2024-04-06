package com.backend.mlopsbackend.Entities;

public class LoginRequest {
    public String Username;
    public String Password;

    public LoginRequest(){}

    public LoginRequest(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }
}
