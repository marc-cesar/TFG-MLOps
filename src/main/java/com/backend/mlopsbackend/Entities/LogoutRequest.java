package com.backend.mlopsbackend.Entities;

public class LogoutRequest {
    public String Username;
    public String Token;

    public LogoutRequest(){}

    public LogoutRequest(String Username, String Token){
        this.Username = Username;
        this.Token = Token;
    }
}
