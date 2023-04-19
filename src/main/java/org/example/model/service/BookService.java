package org.example.model.service;

import org.example.bean.Book;
import org.example.dao.BookDao;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> getAllCatalogue() {
        return bookDao.getAllBooks();
    }
}
