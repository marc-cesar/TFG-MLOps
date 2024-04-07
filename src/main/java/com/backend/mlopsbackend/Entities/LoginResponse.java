package com.backend.mlopsbackend.Entities;

public class LoginResponse {
    public String Username;
    public String Token;
    public Boolean IsAdmin;

    public LoginResponse(){}

    public LoginResponse(String Username, String Token, Boolean isAdmin){
        this.Username = Username;
        this.Token = Token;
        this.IsAdmin = isAdmin;
    }
}
