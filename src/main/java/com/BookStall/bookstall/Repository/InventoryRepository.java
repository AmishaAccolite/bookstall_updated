package com.BookStall.bookstall.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BookStall.bookstall.Model.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("Select u from Inventory u where u.bookName=?1")
    List<Inventory> findInventoryByBookName(String bookName);

    @Query("Select t from Inventory t order by bookLikes desc")
    List<Inventory> findSortedInventoryByBookLikes();

}