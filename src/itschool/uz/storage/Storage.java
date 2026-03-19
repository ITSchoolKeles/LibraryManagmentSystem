package itschool.uz.storage;

import itschool.uz.enums.UserRole;
import itschool.uz.model.Book;
import itschool.uz.model.Transaction;
import itschool.uz.model.User;

public interface Storage {

    User[] USER_STORAGE = new User[20];
    Book[] BOOK_STORAGE = new Book[20];
    Transaction[] TRANSACTION_STORAGE = new Transaction[100];


}
