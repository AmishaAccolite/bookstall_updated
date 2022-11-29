package com.BookStall.bookstall.Service;

import java.util.List;

import com.BookStall.bookstall.Model.User;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    List<User> getUsers();

    User suspendUserById(int userid);

}

