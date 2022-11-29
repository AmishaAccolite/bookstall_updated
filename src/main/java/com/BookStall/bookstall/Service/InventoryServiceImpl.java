package com.BookStall.bookstall.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStall.bookstall.Model.Book;
import com.BookStall.bookstall.Model.Inventory;
import com.BookStall.bookstall.Repository.BookRepository;
import com.BookStall.bookstall.Repository.InventoryRepository;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryrepo;
    @Autowired
    private BookRepository bookrepo;
    public Inventory createInventory(Inventory inv)
    {
        Inventory inventoryAdded=inventoryrepo.save(inv);
        for(int i=0;i<inventoryAdded.getBookCount();i++)
        {
            Book book=new Book();
            book.setClassId(inventoryAdded.getClassId());
            bookrepo.save(book);
            System.out.println(book);
        }
        return inventoryAdded;
    }
    public List<Inventory> getInventories()
    {
        return this.inventoryrepo.findAll();
    }
    public List<Inventory> getInventoryByBookName(String bookName)
    {
        List<Inventory> bookList=inventoryrepo.findInventoryByBookName(bookName);
        for (Inventory inventory : bookList) {
            if(inventory.getBookCount()==0)
                System.out.println("BOOK "+ inventory.getClassId()+" OUT OF STOCK");
        }
        return bookList;
    }
    public List<Inventory> getSortedInventoryByBookLikes()
    {
        return inventoryrepo.findSortedInventoryByBookLikes();
    }
}
