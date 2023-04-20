package org.example.bean;

public class Book {
    private String author;
    private String description;
    private String title;
    private String theme;

    public Book(String title) {
        this(title, null);
    }

    public Book(String title, String author) {
        this(title, author, null);
    }

    public Book(String title, String author, String description) {
        this.author = author;
        this.description = description;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getTheme() {
        return theme;
    }
}
