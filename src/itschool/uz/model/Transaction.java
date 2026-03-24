package itschool.uz.model;

import java.time.LocalDateTime;

public class Transaction {
    private long id;
    private User user;
    private Book book;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public Transaction() {

    }
    public Transaction(long id, User user, Book book, LocalDateTime borrowDate, LocalDateTime returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
    @Override
    public String toString() {
        if(returnDate == null){
            return "Transaction{" +
                    "id=" + id +
                    ", user=" + user.getEmail() +
                    ", book=" + book.getTitle() +
                    ", borrowDate=" + borrowDate.getYear() + "-" + borrowDate.getMonthValue() + "-" + borrowDate.getDayOfMonth() + " " + borrowDate.getHour() + ":" + borrowDate.getMinute() + ":" + borrowDate.getSecond() +
                    ", returnDate=null" +
                    '}';
        }
        return "Transaction{" +
                "id=" + id +
                ", user=" + user.getEmail() +
                ", book=" + book.getTitle() +
                ", borrowDate=" + borrowDate.getYear() + "-" + borrowDate.getMonthValue() + "-" + borrowDate.getDayOfMonth() + " " + borrowDate.getHour() + ":" + borrowDate.getMinute() + ":" + borrowDate.getSecond() +
                ", returnDate=" + returnDate.getYear() + "-" + returnDate.getMonthValue() + "-" + returnDate.getDayOfMonth() + " " + returnDate.getHour() + ":" + returnDate.getMinute() + ":" + returnDate.getSecond() +
                '}';
    }
}
