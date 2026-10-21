package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.ui.GymMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize database table on startup
        DatabaseConnection.initializeDatabase();

        // Launch interactive UI menu handler
        GymMenu menu = new GymMenu();
        menu.startMenu();
    }
}