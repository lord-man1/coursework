package org.example.controller;

import org.example.FIlterSettings;
import org.example.model.Model;
import org.example.view.ConsoleView;

public class Controller {
    private Model model;
    private ConsoleView consoleView;


    public void onShowMenu() {
        consoleView.refreshMenu();
    }

    public void onShowCatalogue() {
        model.loadCatalogue();
        consoleView.refreshCatalogue(model.getModelData());
    }

    public void onShowFilterSettings() {
        consoleView.refreshFilterSettings(model.getModelData());
    }



    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public void onEnableFilter(FIlterSettings authorFilter) {
        model.setFilterSettings(authorFilter);
    }

    public void onExit() {
        model.saveFilterSettings();
    }

    public void onStart() {
        model.loadFilterSettings();
    }
}
