package itschool.uz.service;

public interface TransactionService {
    void createTransaction(long userId, long bookId);
    void transactionsByUserId(long userId);
    void returnBook(long transactionId);
    void viewTransactionsByUserIdAndReturnedDate(long userId);
    void viewAllTransactions();
    void viewCurrentBorrowedBooks(long userId);
}
