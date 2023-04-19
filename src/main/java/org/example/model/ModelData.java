package org.example.model;

import org.example.bean.Book;

import java.io.Serializable;
import java.util.List;

public class ModelData implements Serializable {
    private List<Book> bookList;
    private boolean filteringByAuthor;
    private boolean filteringByDescription;
    private boolean filteringByTitle;
    private boolean filteringBySubject;
    private boolean suggestAllSimilarOptions;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean isFilteringByAuthor() {
        return filteringByAuthor;
    }

    public boolean isFilteringByDescription() {
        return filteringByDescription;
    }

    public boolean isFilteringByTitle() {
        return filteringByTitle;
    }

    public boolean isFilteringBySubject() {
        return filteringBySubject;
    }

    public boolean isSuggestAllSimilarOptions() {
        return suggestAllSimilarOptions;
    }
}
