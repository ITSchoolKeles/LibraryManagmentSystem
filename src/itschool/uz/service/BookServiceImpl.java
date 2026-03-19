package itschool.uz.service;

import itschool.uz.model.Book;
import itschool.uz.storage.Storage;

public class BookServiceImpl implements BookService{
    private long bookLastId = 0;
    @Override
    public void addBook(Book book) {

        if (book.getTitle() == null || book.getTitle().isEmpty()
                || book.getAuthor() == null || book.getAuthor().isEmpty()) {
            System.out.println("Book title and author cannot be empty");
            return;
        }

        book.setId(++bookLastId);
        for (int i = 0; i < Storage.BOOK_STORAGE.length; i++) {
            if (Storage.BOOK_STORAGE[i] == null) {
                Storage.BOOK_STORAGE[i] = book;
                break;
            }
            System.out.println("Book has been successfully added to the system");
        }
    }

    @Override
    public void deleteBook(long id) {
      for(int i = 0; i < Storage.BOOK_STORAGE.length; i++){
          Book book = Storage.BOOK_STORAGE[i];
          if (book != null) {
              if(book.getId() == id) {
                    Storage.BOOK_STORAGE[i] = null;
                    System.out.println("Book has been successfully deleted from the system");
                    return;
              }
          }
      }
        System.out.println("Book with id " + id + " does not exist in the system");
    }

    @Override
    public void  getAllBooks() {
       for(Book book : Storage.BOOK_STORAGE){
           if(book != null){
               System.out.println(book);
           }
       }
    }

    @Override
    public void getBookById(long id) {

    }
}
