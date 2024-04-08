package com.backend.mlopsbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usertokens", schema = "public")
public class UserToken {
    @Id
    public String token;
    public long userId;

    public UserToken(){}
}
