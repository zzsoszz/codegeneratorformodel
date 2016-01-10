package com.bxtel.security5.auth;
import java.util.ArrayList;
import java.util.List;
public interface IAuthenticationResponse<T>{
    T  getUserData();
    ArrayList<String> getAuthorities();
    boolean isAuthenticated();
}