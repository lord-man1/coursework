package org.example.model;

import org.example.bean.Book;
import org.example.model.service.BookService;

import java.util.List;

public class Model {
    private ModelData modelData = new ModelData();
    private BookService bookService = new BookService();

    public void loadCatalogue() {
        modelData.setBookList(getAllBooks());
    }

    public void loadFilterSettings() {

    }

    private List<Book> getAllBooks() {
        return bookService.getAllCatalogue();
    }

    public ModelData getModelData() {
        return modelData;
    }
}
