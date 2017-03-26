package com.daltonicchameleon.portfolio.model;

/**
 * portfolio-app by Carbon by BOLD
 * Created in 3/24/17 the following authors:
 * Pedro Okawa - {pedrookawa@carbonbybold.com}
 */
public class User {

    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}