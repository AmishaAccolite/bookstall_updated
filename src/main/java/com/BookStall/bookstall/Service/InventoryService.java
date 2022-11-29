package com.BookStall.bookstall.Service;

import java.util.List;

import com.BookStall.bookstall.Model.Book;
import com.BookStall.bookstall.Model.Inventory;

public interface InventoryService {

    Inventory createInventory(Inventory inventory);


    List<Inventory> getInventories();

    List<Inventory> getInventoryByBookName(String bookName);

    List<Inventory> getSortedInventoryByBookLikes();

}
