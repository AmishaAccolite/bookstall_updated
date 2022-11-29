package com.BookStall.bookstall.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.BookStall.bookstall.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{


}
