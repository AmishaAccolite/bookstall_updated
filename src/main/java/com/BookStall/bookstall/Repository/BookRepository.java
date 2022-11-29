package com.BookStall.bookstall.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookStall.bookstall.Model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
