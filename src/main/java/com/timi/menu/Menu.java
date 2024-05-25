package com.timi.menu;

import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;
import com.timi.model.User;

public class Menu {

    private static User loggedInUser = null;
    private static Menu loginMenu = new LoginMenu();
    private static Menu adminMenu = new AdminMenu();
    private static Menu instructorMenu = new InstructorMenu();
    private static Menu studentMenu = new StudentMenu();
    
    protected Menu createInstance() {
        return new Menu();
    }

    public void show() throws DAOException, InvalidEmailException {
        while (true) {
            if (loggedInUser == null) {
                loginMenu.show();
            } else {
                switch (loggedInUser.getRole()) {
                    case "ADMIN":
                        adminMenu.show();
                        break;
                    case "INSTRUCTOR":
                        instructorMenu.show();
                        break;
                    case "STUDENT":
                        studentMenu.show();
                        break;
                }
            
            }
        }
    }
}

