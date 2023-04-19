package org.example.view;

import org.example.bean.Book;
import org.example.controller.Controller;
import org.example.model.ModelData;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleView implements View {
    private final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    private Controller controller;

    public ConsoleView() {
    }

    public void refreshMenu() {
        System.out.println("1. Каталог книг");
        System.out.println("2. Поиск книги");
        System.out.println("3. Настройки фильтрации");
        System.out.println("4. Выход");
    }

    public void refreshCatalogue(ModelData modelData) {
        for (Book book : modelData.getBookList()) {
            System.out.println(book);
        }
    }

    public void refreshFilterSettings(ModelData modelData) {
        String filterAuthor = modelData.isFilteringByAuthor() ? "1. Фильтрация по автору [*]" : "1. Фильтрация по автору [ ]";
        String filterDescr = modelData.isFilteringByDescription() ? "2. Фильтрация по описанию [*]" : "2. Фильтрация по описанию [ ]";
        String filterTitle = modelData.isFilteringByTitle() ? "3. Фильтрация по названию [*]" : "3. Фильтрация по названию [ ]";
        String filterTheme = modelData.isFilteringBySubject() ? "4. Фильтрация по предметной рубрике [*]" : "4. Фильтрация по предметной рубрике [ ]";
        String suggestVars = modelData.isSuggestAllSimilarOptions() ? "5. Предлагать ВСЕ схожие варианты [*]" : "5. Предлагать ВСЕ схожие варианты [ ]";
        System.out.println(filterAuthor + "\n" + filterDescr + "\n" + filterTitle + "\n" + filterTheme + "\n" + suggestVars);
    }

    @Override
    public void fireEventShowMenu() {
        controller.onShowMenu();
    }

    @Override
    public void fireEventShowCatalogue() {
        controller.onShowCatalogue();
    }

    @Override
    public void fireEventShowSearch() {
    }

    @Override
    public void fireEventShowFilterSettings() {
        controller.onShowFilterSettings();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
