package org.example.controller;

import org.example.FilterSettings;
import org.example.MenuPoints;
import org.example.model.Model;
import org.example.view.ConsoleView;

import java.io.IOException;
import java.util.Map;

public class Controller {
    private Model model;
    private ConsoleView consoleView;


    public void onShowMenu(MenuPoints point) throws IOException {
        switch (point) {
            case CATALOGUE -> consoleView.fireEventShowCatalogue();
            case SEARCH -> consoleView.fireEventShowSearchMenu();
            case FILTER_SETTINGS -> consoleView.fireEventShowFilterSettings();
            case EXIT -> {
                consoleView.fireEventExit();
                System.exit(0);
            }
        }
    }

    public void onShowSearchMenu() throws IOException {
        consoleView.refreshSearchMenu(model.getModelData());
    }

    public void onShowCatalogue() {
        model.loadCatalogue();
        consoleView.refreshCatalogue(model.getModelData());
    }

    public void onShowFilterSettings(FilterSettings filterSetting) {
        if (filterSetting == null) {
            consoleView.refreshFilterSettings(model.getModelData());
            return;
        }
        onEnableFilter(filterSetting);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public void onEnableFilter(FilterSettings filter) {
        model.setFilterSettings(filter);
    }

    public void onExit() {
        model.saveFilterSettings();
    }

    public void onStart() {
        model.loadFilterSettings();
    }

    public void onShowSearchResults(Map<FilterSettings, String> inputData) {
        model.loadSearchResults(inputData);
        consoleView.refreshSearchResults(model.getModelData());
    }
}
