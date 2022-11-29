package com.BookStall.bookstall.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="book")
public class Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name="class_id")
    private int classId;
    @Column(name="book_status")
    private boolean bookStatus;
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }
    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }
    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", classId=" + classId + "]";
    }


}

