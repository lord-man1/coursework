package org.example.view;

import org.example.FilterSettings;
import org.example.MenuPoints;
import org.example.bean.Book;
import org.example.controller.Controller;
import org.example.model.ModelData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
        String exit = "6. Выход из меню настроек";
        System.out.println("\n" + filterAuthor + "\n" + filterDescr + "\n" + filterTitle + "\n" + filterTheme + "\n" + suggestVars + "\n" + exit);
    }

    public void fireEventShowMenu() throws IOException {
        String mainMenuPoint;
        fireEventStart();
        while (true) {
            refreshMenu();
            System.out.print("Введите пункт меню -> ");
            mainMenuPoint = consoleReader.readLine();
            switch (mainMenuPoint) {
                case "1" -> {
                    System.out.println("\n");
                    controller.onShowMenu(MenuPoints.CATALOGUE);
                }
                case "2" -> {
                    System.out.println("\n");
                    controller.onShowMenu(MenuPoints.SEARCH);
                }
                case "3" -> {
                    controller.onShowMenu(MenuPoints.FILTER_SETTINGS);
                }
                case "4" -> controller.onShowMenu(MenuPoints.EXIT);
                default -> System.out.println("\n\nТакого пункта нет! Повторите ввод.");
            }
            System.out.println("\n");
        }
    }

    public void refreshSearchMenu(ModelData modelData) throws IOException {
        Map<FilterSettings, String> inputData = new HashMap<>();
        if (modelData.isFilteringByTitle()) {
            System.out.println("Введите название: ");
            inputData.put(FilterSettings.TITLE_FILTER, consoleReader.readLine());
        }
        if (modelData.isFilteringByAuthor()) {
            System.out.println("Введите автора: ");
            inputData.put(FilterSettings.AUTHOR_FILTER, consoleReader.readLine());
        }
        if (modelData.isFilteringBySubject()) {
            System.out.println("Введите тему: ");
            inputData.put(FilterSettings.THEME_FILTER, consoleReader.readLine());
        }
        if (modelData.isFilteringByDescription()) {
            System.out.println("Введите описание: ");
            inputData.put(FilterSettings.DESCRIPTION_FILTER, consoleReader.readLine());
        }
        if (inputData.size() == 0) {
            System.out.println("Нет ни одного параметра для поиска! (Настройки фильтрации)");
            return;
        }
        controller.onShowSearchResults(inputData);
    }

    public void fireEventShowCatalogue() {
        controller.onShowCatalogue();
    }

    public void fireEventShowSearchMenu() throws IOException {
        controller.onShowSearchMenu();
    }

    public void fireEventShowFilterSettings() throws IOException {
        String settingsMenuPoint;
        boolean flag = true;
        while (flag) {
            controller.onShowFilterSettings(null);
            System.out.print("Введите пункт меню настроек -> ");
            settingsMenuPoint = consoleReader.readLine();
            switch (settingsMenuPoint) {
                case "1" -> controller.onShowFilterSettings(FilterSettings.AUTHOR_FILTER);
                case "2" -> controller.onShowFilterSettings(FilterSettings.DESCRIPTION_FILTER);
                case "3" -> controller.onShowFilterSettings(FilterSettings.TITLE_FILTER);
                case "4" -> controller.onShowFilterSettings(FilterSettings.THEME_FILTER);
                case "5" -> controller.onShowFilterSettings(FilterSettings.SIMILAR_FILTER);
                case "6" -> flag = false;
                default -> System.out.println("\n\nТакого пункта нет! Повторите ввод.");
            }
        }

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventEnableFilter(FilterSettings filter) {
        controller.onEnableFilter(filter);
    }

    public void fireEventExit() {
        controller.onExit();
    }

    public void fireEventStart() {
        controller.onStart();
    }
}
