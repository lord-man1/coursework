package org.example.model;

import org.example.FIlterSettings;
import org.example.bean.Book;
import org.example.model.service.BookService;

import java.io.*;
import java.util.List;

public class Model {
    private ModelData modelData = new ModelData();
    private BookService bookService = new BookService();

    public void loadCatalogue() {
        modelData.setBookList(getAllBooks());
    }

    public void loadFilterSettings() {
        try (FileInputStream fis = new FileInputStream(ModelData.PATH_TO_SETTINGS);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            modelData = (ModelData) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    private List<Book> getAllBooks() {
        return bookService.getAllCatalogue();
    }

    public ModelData getModelData() {
        return modelData;
    }

    public void setFilterSettings(FIlterSettings authorFilter) {
        boolean bool;
        switch (authorFilter) {
            case AUTHOR_FILTER -> {
                bool = enableOrDisableSettings(modelData.isFilteringByAuthor());
                modelData.setFilteringByAuthor(bool);
            }
            case DESCRIPTION_FILTER -> {
                bool = enableOrDisableSettings(modelData.isFilteringByDescription());
                modelData.setFilteringByDescription(bool);
            }
            case TITLE_FILTER -> {
                bool = enableOrDisableSettings(modelData.isFilteringByTitle());
                modelData.setFilteringByTitle(bool);
            }
            case THEME_FILTER -> {
                bool = enableOrDisableSettings(modelData.isFilteringBySubject());
                modelData.setFilteringBySubject(bool);
            }
            case SIMILAR_FILTER -> {
                bool = enableOrDisableSettings(modelData.isSuggestAllSimilarOptions());
                modelData.setSuggestAllSimilarOptions(bool);
            }
        }
    }

    private boolean enableOrDisableSettings(boolean isActive) {
        return !isActive;
    }

    public void saveFilterSettings() {
        try (FileOutputStream fos = new FileOutputStream(ModelData.PATH_TO_SETTINGS);
             ObjectOutputStream ous = new ObjectOutputStream(fos)) {
              ous.writeObject(modelData);
        } catch (IOException ignored) {
        }
    }
}
