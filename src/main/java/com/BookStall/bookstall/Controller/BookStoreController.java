package com.BookStall.bookstall.Controller;
import java.util.List;

import com.BookStall.bookstall.Repository.InventoryRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BookStall.bookstall.Exceptions.NoObjectFoundException;
import com.BookStall.bookstall.Model.Inventory;
import com.BookStall.bookstall.Model.User;
import com.BookStall.bookstall.Service.InventoryService;
import com.BookStall.bookstall.Service.UserService;


@RestController
public class BookStoreController {
    @Autowired
    private InventoryService invService;

    @GetMapping("/allbooks")
    private ResponseEntity<List<Inventory>> getAllInventories()
    {
        return ResponseEntity.ok().body(this.invService.getInventories());
    }

    @PostMapping("/addbooks")
    private ResponseEntity<Inventory> saveInventories(@RequestBody Inventory inv)
    {
        try {
            return ResponseEntity.ok().body(this.invService.createInventory(inv));
        } catch (Exception e) {
            System.out.println("Check the inputs... "+e.getStackTrace());
        }
        return null;
    }
    @GetMapping("/books/{bookName}")
    private ResponseEntity<List<Inventory>> getInventoryByBookName(@PathVariable String bookName)
    {
        return ResponseEntity.ok().body(this.invService.getInventoryByBookName(bookName));
    }
    @GetMapping("/books/sortbylikes")
    private ResponseEntity<List<Inventory>> getSortedInventoryLikes()
    {
        return ResponseEntity.ok().body(this.invService.getSortedInventoryByBookLikes());
    }

}

