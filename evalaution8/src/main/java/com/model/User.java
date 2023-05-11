package com.model;


/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class User {
    protected int id;
    protected String name;
    protected String email;
    protected String country;
    protected String img;

    public User() {
    }

    public User(String name, String email, String country,String img) {
        super();
        this.name = name;
        this.email = email;
        this.country = country;
        this.img = img;
    }

    public User(int id, String name, String email, String country,String img) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}