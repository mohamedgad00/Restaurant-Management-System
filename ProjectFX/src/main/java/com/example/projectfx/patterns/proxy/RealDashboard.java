package com.example.projectfx.patterns.proxy;

/**
 * Real Subject
 */
public class RealDashboard implements Dashboard {

    @Override
    public void displayDashboard() {
        System.out.println("🖥️ Displaying real dashboard with all features...");
    }

    public void showAdminPanel() {
        System.out.println("🔧 Showing admin panel...");
    }

    public void showReports() {
        System.out.println("📊 Showing financial reports...");
    }
}
