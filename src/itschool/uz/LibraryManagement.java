package itschool.uz;

import itschool.uz.enums.UserRole;
import itschool.uz.model.User;
import itschool.uz.service.AuthService;
import itschool.uz.service.AuthServiceImpl;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.UUID;

public class LibraryManagement {
    private static final Scanner INPUT_FOR_INT = new Scanner(System.in);
    private static final Scanner INPUT_FOR_STRING = new Scanner(System.in);
    private static final AuthService authService = new AuthServiceImpl();
    public static void main(String[] args) {
         displayMenu();
        int menuNumber = getMenuNumber();
        switch(menuNumber){
            case 1 -> System.out.println("login menu is selected");
            case 2 ->register();
            case 0 -> System.exit(0);
            default -> System.out.println("Invalid menu number");
        }

    }
    public static void displayMenu(){
        System.out.println("Welcome to our Library Management System");
        System.out.println("----------------------------------------------");
        System.out.println("Please enter the menu number");
        System.out.println("1.Login");
        System.out.println("2.Register");
        System.out.println("0.Exit");
    }
    public static int getMenuNumber(){
        System.out.print("Enter the number --> ");
        return INPUT_FOR_INT.nextInt();
    }
    public static User login(){

        return null;
    }

    public static User register(){
        System.out.println();
        System.out.println("THis is the register page ");
        System.out.println("----------------------------------------------");
        System.out.print("Enter your fullName --> ");
        String fullName = INPUT_FOR_STRING.nextLine();

        System.out.print("Enter your email --> ");
        String email = INPUT_FOR_STRING.nextLine();

        System.out.print("Enter your password --> ");
        String password = INPUT_FOR_STRING.nextLine();

        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(UserRole.USER);

        User registeredUser = authService.register(newUser);
        System.out.println(registeredUser);
        return registeredUser;

    }

}
