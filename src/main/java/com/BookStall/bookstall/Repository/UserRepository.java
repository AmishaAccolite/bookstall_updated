package com.BookStall.bookstall.Repository;

import javax.transaction.Transactional;

import com.BookStall.bookstall.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.BookStall.bookstall.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("Select i from User i where i.userId=?1")
    User findUserByUserId(int userId);

}
