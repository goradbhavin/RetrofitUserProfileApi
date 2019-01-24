package com.gb.myuserprofileapi.api;

import com.gb.myuserprofileapi.model.User;

import java.util.List;

/**
 * Created by bhavin on 8/13/2018.
 */

public class LoginResponse {
    boolean error;
    String message;
    User user;

    public LoginResponse(boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
