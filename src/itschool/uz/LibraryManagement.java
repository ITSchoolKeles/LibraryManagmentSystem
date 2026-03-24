package itschool.uz;

import itschool.uz.enums.BookStatus;
import itschool.uz.enums.UserRole;
import itschool.uz.model.Book;
import itschool.uz.model.User;
import itschool.uz.service.*;

import java.util.Scanner;

public class LibraryManagement {
    private static final Scanner INPUT_FOR_INT = new Scanner(System.in);
    private static final Scanner INPUT_FOR_STRING = new Scanner(System.in);
    private static final AuthService authService = new AuthServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final TransactionService transactionService = new TransactionServiceImpl();
    private static User currentUser = null;
    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int menuNumber = getMenuNumber();
            switch (menuNumber) {
                case 1 -> currentUser = login();
                case 2 -> currentUser = register();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid menu number");
            }
            if(currentUser != null && currentUser.getRole() == UserRole.ADMIN) {
                while (true) {
                    adminDashboard();
                    int getAdminMenuNumber = getMenuNumber();
                    switch (getAdminMenuNumber) {
                        case 1 -> addBook();
                        case 2 -> viewAllBooks();
                        case 3 -> deleteBook();
                    }
                    if(getAdminMenuNumber == 0){
                        System.out.println("Logging out...");
                        currentUser = null;
                        break;
                    }
                }

            }
            else if(currentUser != null && currentUser.getRole() == UserRole.USER) {
                while (true) {
                    userDashboard();
                    int userMenuNumber = getMenuNumber();
                    switch (userMenuNumber) {
                        case 1 -> viewAllBooks();
                        case 2 -> borrowBook();
                        case 3 -> returnBook();
                        case 4 -> viewBorrowedBooks();
                        case 5 -> myTransactionHistory();

                    }
                    if(userMenuNumber == 0){
                        System.out.println("Logging out...");
                        currentUser = null;
                        break;
                    }
                }
            }


        }
    }

    private static void myTransactionHistory() {
        System.out.println("This is the transaction history page");
        System.out.println("----------------------------------------------");

        transactionService.transactionsByUserId(currentUser.getId());
    }

    private static void viewBorrowedBooks() {
        System.out.println("This is the view borrowed books page");
        System.out.println("----------------------------------------------");
    }

    private static void returnBook() {
        System.out.println("This is the return book page");
        System.out.println("----------------------------------------------");

        transactionService.viewTransactionsByUserIdAndReturnedDate(currentUser.getId());
        int transactionId = getMenuNumber();

        transactionService.returnBook(transactionId);



    }

    private static void borrowBook() {
        System.out.println("This is the borrow book page");
        System.out.println("----------------------------------------------");

        viewAllBooks();

        int bookId = getMenuNumber();

        transactionService.createTransaction(currentUser.getId(), bookId);

    }

    private static void deleteBook() {
        bookService.getAllBooks();
        int bookId = getMenuNumber();

        bookService.deleteBook(bookId);

    }

    private static void viewAllBooks() {
         bookService.getAllBooks();
    }

    private static void addBook() {
        System.out.println("This is the add book page");
        System.out.println("----------------------------------------------");

        System.out.print("Enter book title --> ");
        String title = INPUT_FOR_STRING.nextLine();

        System.out.print("Enter book author --> ");
        String author = INPUT_FOR_STRING.nextLine();

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setStatus(BookStatus.AVAILABLE);

        bookService.addBook(newBook);
    }

    ///  Admin panelni qilish // kitob qosish
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
        System.out.println();
        System.out.println("THis is the login page ");
        System.out.println("----------------------------------------------");

        System.out.print("Enter your email --> ");
        String email = INPUT_FOR_STRING.nextLine();

        System.out.print("Enter your password --> ");
        String password = INPUT_FOR_STRING.nextLine();

        User logedUser = authService.login(email, password);

        if(logedUser == null){
            System.out.println("Login failed.");
            return logedUser;
        }
        System.out.println("----------------------------------------------");
        System.out.println("Login successful!");
        System.out.println(logedUser);
        return logedUser;
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
        if(registeredUser == null){
            System.out.println("Registration failed.");
            return registeredUser;
        }
        System.out.println("----------------------------------------------");
        System.out.println("Registration successful!");
        return registeredUser;

    }

    public static void adminDashboard(){
        System.out.println();
        System.out.println("Welcome to the Admin Dashboard");
        System.out.println("----------------------------------------------");

        System.out.println("1. Add a book");
        System.out.println("2. View all books");
        System.out.println("3. Delete a book");
        System.out.println("4. View all transactions");// todo barcha transactionlarni korish
        System.out.println("5. View certain user transaction"); // todo ayni bir userni transactionlarni ko'rish
        System.out.println("0. Logout");

    }

    // todo User dashboarni shakllantirish

    public static void userDashboard(){
        System.out.println();
        System.out.println("Welcome to the User Dashboard");
        System.out.println("----------------------------------------------");

        System.out.println("1. View all books");  // done
        System.out.println("2. Borrow a book"); // done // todo faqatgina availablelarni chiqarish
        System.out.println("3. Return a book"); //done
        System.out.println("4. View my borrowed books");   // todo
        System.out.println("5. Transaction history"); // done
        System.out.println("0. Logout");
    }



}
