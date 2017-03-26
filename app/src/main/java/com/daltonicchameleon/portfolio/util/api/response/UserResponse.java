package com.daltonicchameleon.portfolio.util.api.response;

import com.daltonicchameleon.portfolio.model.User;

/**
 * portfolio-app by Carbon by BOLD
 * Created in 3/24/17 the following authors:
 * Pedro Okawa - {pedrookawa@carbonbybold.com}
 */
public class UserResponse {

    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
