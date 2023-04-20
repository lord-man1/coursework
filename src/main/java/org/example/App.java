package org.example;

import org.example.controller.Controller;
import org.example.model.Model;
import org.example.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Model model = new Model();
        Controller controller = new Controller();
        ConsoleView consoleView = new ConsoleView();
        consoleView.setController(controller);
        controller.setView(consoleView);
        controller.setModel(model);
        run(consoleView);
    }

    public static void run(ConsoleView consoleView) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        consoleView.fireEventStart();
        while (true) {
            consoleView.fireEventShowMenu();
            System.out.print("Введите пункт меню -> ");
            handleMainMenuInput(consoleView, consoleReader);
        }
    }

    public static void handleMainMenuInput(ConsoleView consoleView, BufferedReader consoleReader) throws IOException {
        String mainMenuPoint;
        mainMenuPoint = consoleReader.readLine();
        switch (mainMenuPoint) {
            case "1" -> {
                System.out.println("\n");
                consoleView.fireEventShowCatalogue();
            }
            case "2" -> {
                System.out.println("\n");
                consoleView.fireEventShowSearch();
            }
            case "3" -> handleSettingsMenuInput(consoleView, consoleReader);
            case "4" -> {
                consoleView.fireEventExit();
                System.exit(0);
            }
            default -> System.out.println("\n\nТакого пункта нет! Повторите ввод.");
        }
        System.out.println("\n");
    }

    public static void handleSettingsMenuInput(ConsoleView consoleView, BufferedReader consoleReader) throws IOException {
        String settingsMenuPoint;
        boolean flag = true;
        while (flag) {
            System.out.println("\n");
            consoleView.fireEventShowFilterSettings();
            System.out.print("Введите пункт меню настроек -> ");
            settingsMenuPoint = consoleReader.readLine();
            switch (settingsMenuPoint) {
                case "1" -> consoleView.fireEventEnableFilter(FIlterSettings.AUTHOR_FILTER);
                case "2" -> consoleView.fireEventEnableFilter(FIlterSettings.DESCRIPTION_FILTER);
                case "3" -> consoleView.fireEventEnableFilter(FIlterSettings.TITLE_FILTER);
                case "4" -> consoleView.fireEventEnableFilter(FIlterSettings.THEME_FILTER);
                case "5" -> consoleView.fireEventEnableFilter(FIlterSettings.SIMILAR_FILTER);
                case "6" -> flag = false;
                default -> System.out.println("\n\nТакого пункта нет! Повторите ввод.");
            }
        }
    }
}
