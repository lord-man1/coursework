package org.example.dao;

import org.example.FilterSettings;
import org.example.bean.Book;

import java.util.List;
import java.util.Map;

public class BookDao {
    private final DataSource dataSource = DataSource.getInstance();

    public List<Book> getAllBooks() {
        return dataSource.getCatalogue();
    }

    public Book getBookByTitle(String title) {
        List<Book> books = dataSource.getCatalogue();
        for (Book book : books) {
            if (book.getTitle().equals(title)) return book;
        }
        return null;
    }
}
