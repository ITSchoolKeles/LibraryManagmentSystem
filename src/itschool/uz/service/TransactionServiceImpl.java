package itschool.uz.service;

import itschool.uz.enums.BookStatus;
import itschool.uz.model.Book;
import itschool.uz.model.Transaction;
import itschool.uz.model.User;
import itschool.uz.storage.Storage;

import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService{
    private long transactionId = 0;
    @Override
    public void createTransaction(long userId, long bookId) {
        User orderer = null;
        Book borrowedBook = null;
        for(User user: Storage.USER_STORAGE){
            if(user != null && user.getId() == userId){
                orderer = user;
            }
        }
        if(orderer == null){
            System.out.println("User is not found with id " + userId);
            return;
        }
        for(Book book : Storage.BOOK_STORAGE){
            if(book != null && book.getId() == bookId){
                borrowedBook = book;
            }
        }
        if(borrowedBook == null){
            System.out.println("Book is not found with id " + bookId);
            return;
        }

        // check whether book is available or not
        if(borrowedBook.getStatus() == BookStatus.BORROWED){
            System.out.println("Book with id " + bookId + " is already borrowed by another user");
            return;
        }

// create transaction with existing data(user, book)
        Transaction newTransaction = new Transaction();
        newTransaction.setId(++transactionId);
        newTransaction.setBook(borrowedBook);
        newTransaction.setUser(orderer);
        newTransaction.setBorrowDate(LocalDateTime.now());

        // CHANGE BOOK STATUS TO BORROWED
        borrowedBook.setStatus(BookStatus.BORROWED);


        // save transaction in vacant(null) space of the array
      for(int i = 0; i < Storage.TRANSACTION_STORAGE.length; i++){
          if(Storage.TRANSACTION_STORAGE[i] == null){
              Storage.TRANSACTION_STORAGE[i] = newTransaction;
              break;
          }
      }
        System.out.println("Book with id " + bookId + " has been successfully borrowed by user with id " + userId);


    }

    @Override
    public void transactionsByUserId(long userId) {
        for(Transaction transaction : Storage.TRANSACTION_STORAGE){
            if(transaction != null && transaction.getUser().getId() == userId){
                System.out.println(transaction);
            }
        }
    }

    @Override
    public void returnBook(long transactionId) {
          Transaction transaction = null;

          for(Transaction t : Storage.TRANSACTION_STORAGE){
              if(t != null && t.getId() == transactionId){
                    transaction = t; break;
              }
          }
          if(transaction == null){
              System.out.println("Transaction is not found with id " + transactionId);
              return;
          }
          transaction.setReturnDate(LocalDateTime.now());

          transaction.getBook().setStatus(BookStatus.AVAILABLE);

        System.out.println("Book with name " + transaction.getBook().getTitle() + " has been successfully returned by user with name " + transaction.getUser().getFullName());

    }

    @Override
    public void viewTransactionsByUserIdAndReturnedDate(long userId) {
        for(Transaction transaction : Storage.TRANSACTION_STORAGE){
            if(transaction != null){
                if(transaction.getUser().getId() == userId && transaction.getReturnDate() == null){
                    System.out.println(transaction);
                }
            }
        }
    }


}
