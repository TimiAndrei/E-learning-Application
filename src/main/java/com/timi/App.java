package com.timi;

import com.timi.menu.Menu;
import com.timi.service.DataService;
import com.timi.service.impl.DataServiceImpl;
import com.timi.exception.DAOException;
import com.timi.exception.InvalidEmailException;

public class App {

    public static void main(String[] args) throws Exception {
        DataService dataService = new DataServiceImpl();

        try {
            dataService.loadDatabase();
        } catch (DAOException e) {
            System.out.println("Error loading data from the database: " + e.getMessage());
        }

        Menu mainMenu = Menu.getInstance();

        try {
            mainMenu.show();
        } catch (DAOException | InvalidEmailException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}