package org.example.model;

import org.example.bean.Book;

import java.io.Serializable;
import java.util.List;

public class ModelData implements Serializable {

    public static final String PATH_TO_SETTINGS = "C:\\JavaProjects\\Coursework\\localSave.txt";
    private transient List<Book> bookList;
    private transient List<String> searchResults;
    private boolean filteringByAuthor;
    private boolean filteringByDescription;
    private boolean filteringByTitle;
    private boolean filteringBySubject;
    private boolean suggestAllSimilarOptions;

    public List<Book> getBookList() {
        return bookList;
    }

    public List<String> getSearchResults() {
        return searchResults;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setSearchResults(List<String> searchResults) {
        this.searchResults = searchResults;
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

    public void setFilteringByAuthor(boolean filteringByAuthor) {
        this.filteringByAuthor = filteringByAuthor;
    }

    public void setFilteringByDescription(boolean filteringByDescription) {
        this.filteringByDescription = filteringByDescription;
    }

    public void setFilteringByTitle(boolean filteringByTitle) {
        this.filteringByTitle = filteringByTitle;
    }

    public void setFilteringBySubject(boolean filteringBySubject) {
        this.filteringBySubject = filteringBySubject;
    }

    public void setSuggestAllSimilarOptions(boolean suggestAllSimilarOptions) {
        this.suggestAllSimilarOptions = suggestAllSimilarOptions;
    }
}
