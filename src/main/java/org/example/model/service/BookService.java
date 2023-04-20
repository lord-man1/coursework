package org.example.model.service;

import org.example.FilterSettings;
import org.example.bean.Book;
import org.example.dao.BookDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> getAllCatalogue() {
        return bookDao.getAllBooks();
    }

    public List<String> getCatalogueWithFilter(Set<FilterSettings> inputData) {
        boolean title = false, author = false, theme = false, description = false;
        for (FilterSettings filterSetting : inputData) {
            switch (filterSetting) {
                case TITLE_FILTER -> title = true;
                case AUTHOR_FILTER -> author = true;
                case THEME_FILTER -> theme = true;
                case DESCRIPTION_FILTER -> description = true;
            }
        }

        List<Book> allBooks = getAllCatalogue();
        List<String> booksWithFilter = new ArrayList<>();
        for (Book book : allBooks) {
            StringBuilder builder = new StringBuilder();
            if (title) builder.append(book.getTitle()).append(" ");
            if (author) builder.append(book.getAuthor()).append(" ");
            if (theme) builder.append(book.getTheme()).append(" ");
            if (description) builder.append(book.getDescription()).append(" ");
            booksWithFilter.add(builder.toString());
        }
        return booksWithFilter;
    }
}
