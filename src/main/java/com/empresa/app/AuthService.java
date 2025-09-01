package com.empresa.app;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, String> users = new HashMap<>();

    public void register(String username, String password) {
        users.put(username, password);
    }
    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}