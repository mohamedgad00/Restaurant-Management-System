package com.example.projectfx.util;

public class SessionManager {
    private static SessionManager instance;
    private String currentUser;
    private String userRole;

    private SessionManager() {
        System.out.println("✅ Module 1: SessionManager created (Singleton)");
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(String user) {
        this.currentUser = user;
    }

    public void setUserRole(String role) {
        this.userRole = role;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getUserRole() {
        return userRole;
    }

    public void clearSession() {
        System.out.println("🚪 Session cleared for: " + currentUser);
        this.currentUser = null;
    }
}