package com.taskmanager.view;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements MenuChoice {
    public String title;

    public ArrayList<MenuChoice> menuList = new ArrayList<>(); //have an arraylist of menu choices

    public static final Scanner input = new Scanner(System.in); //used for reading in user input

    public Menu(String title, Menu parent) {
        this.title = title;
    }

    /**
     * @return return the title
     */
    @Override
    public String getText() {
        return title;
    }


    /**
     * display and obtain the choice while processChoice is true
     */
    @Override
    public void run() {
        int choice = -1;
        do {
            displayChoice();
            choice = obtainChoice();
        } while (processChoice(choice));
    }

    /**
     * Adds the new menu choice to the arraylist
     */
    public void addChoice(MenuChoice choice) {
        this.menuList.add(choice);
    }


    /**
     * Display the menu choice using a string builder
     */
    public void displayChoice() {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");

        if (!this.title.equals("")) {
            builder.append(this.getText()).append("\n");
        }

        for (int i = 0; i < this.menuList.size(); i++) {
            builder.append("\n").append(i + 1).append(".\t").append(this.menuList.get(i).getText());
        }

        builder.append("\n> ");

        System.out.print(builder);
    }


    /**
     * @return the size of the arraylist
     */
    public int getExitIndex() {
        return menuList.size();
    }

    /**
     * The choice must be within 0 and size() -1
     * @return the choice index
     */
    public int obtainChoice() {

            String in = input.nextLine();
            int choice = Integer.parseInt(in) - 1;
            if(choice<=menuList.size()){
            return choice;}
            else {
                System.out.println();
                System.out.println("incorrect choice please choose again");
                return -1;}

    }

    /**
     * if the choice is invalid then false will be returned
     * Otherwise the run() method is called and true is returned
     * @param choice integer is read in for processing
     * @return true or false
     */
    public boolean processChoice(int choice) {
        if (choice == getExitIndex()||choice==-1) {
            run();
            return false;
        }
        menuList.get(choice).run();
        return true;
    }
}
