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
        consoleView.fireEventShowMenu();
    }
}
