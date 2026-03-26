package itschool.uz.service;

import itschool.uz.model.Book;

public interface BookService {
    void addBook(Book book);
    void deleteBook(long id);
    void getAllBooks();
    void getBookById(long id);
    void getAvailableBooks();

}
