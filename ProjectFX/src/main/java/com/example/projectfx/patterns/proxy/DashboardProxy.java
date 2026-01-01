package com.example.projectfx.patterns.proxy;

/**
 * Proxy Pattern - Protection Proxy
 * التحكم في الوصول للـ Dashboard
 */
public class DashboardProxy implements Dashboard {

    private RealDashboard realDashboard;
    private final String username;
    private final String role; // admin, manager, staff, guest

    public DashboardProxy(String username, String password) {
        this.username = username;
        this.role = authenticate(username, password);

        if (hasAccess()) {
            System.out.println("✅ Access granted to " + username + " [" + role + "]");
        } else {
            System.out.println("⛔ Access denied for " + username);
        }
    }

    // ================= Authentication =================
    private String authenticate(String username, String password) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "admin";
        }
        if ("manager".equals(username) && "manager123".equals(password)) {
            return "manager";
        }
        if ("staff".equals(username) && "staff123".equals(password)) {
            return "staff";
        }
        return "guest";
    }

    // ================= Authorization =================
    private boolean hasAccess() {
        return !"guest".equals(role);
    }

    // ================= Proxy Method =================
    @Override
    public void displayDashboard() {

        if (!hasAccess()) {
            System.out.println("⛔ No dashboard access for " + username);
            return;
        }

        if (realDashboard == null) {
            realDashboard = new RealDashboard(); // Lazy Init
        }

        realDashboard.displayDashboard();

        //  Permission Based on Your Role
        switch (role) {
            case "admin":
                realDashboard.showAdminPanel();
                realDashboard.showReports();
                break;

            case "manager":
                realDashboard.showReports();
                break;

            default:
                break;
        }
    }

    // ================= Utilities =================
    public String getUserRole() {
        return role;
    }

    public boolean isAdmin() {
        return "admin".equals(role);
    }
    public boolean loginSuccessful() {
        return hasAccess();
    }

    public void logout() {
        System.out.println("🚪 " + username + " logged out");
        realDashboard = null;
    }
}
