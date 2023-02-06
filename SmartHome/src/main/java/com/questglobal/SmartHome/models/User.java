package com.questglobal.SmartHome.models;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@RequiredArgsConstructor
@Data
@Document("Users")
public class User {

    @Field("_id")
    @Id
    private ObjectId id;
    @Field("UserName")
    private String username;
    @Field("NormalizedUserName")
    private String normalizedUserName;
    @Field("Email")
    private String email;
    @Field("NormalizedEmail")
    private String normalizedEmail;
    @Field("EmailConfirmed")
    private boolean emailConfirmed;
    @Field("PasswordHash")
    private String passwordHash;
    @Field("Rooms")
    private ArrayList<ObjectId> rooms;
    @Field("FirstName")
    private String firstname;
    @Field("LastName")
    private String lastname;
    @Field("Devices")
    private ArrayList<ObjectId> devices;
    @Field("isRoot")
    private boolean isRoot;
    @Field("Houses")
    private ArrayList<ObjectId> houses;
    @Field("roles")
    @DBRef
    private ArrayList<Role> roles;


    public User(String username, String email, String passwordHash, String lastName, String firstName) {
        this.id = id;
        this.username = username;
        this.normalizedUserName = normalizedUserName;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.normalizedEmail = normalizedEmail;
        this.passwordHash = passwordHash;
        this.roles = new ArrayList<Role>();
        this.houses = new ArrayList<ObjectId>();
        this.devices = new ArrayList<ObjectId>();
        this.roles = new ArrayList<Role>();
        this.isRoot = true;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

}
