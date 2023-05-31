package org.example.model;

import org.example.FilterSettings;
import org.example.bean.Book;
import org.example.model.service.BookService;

import java.io.*;
import java.util.*;

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

    public void setFilterSettings(FilterSettings filter) {
        boolean bool;
        switch (filter) {
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

    public void loadSearchResults(Map<FilterSettings, String> inputData) {
        modelData.setSearchResults(getBooksWithFilters(inputData));
    }

    public List<String> getBooksWithFilters(Map<FilterSettings, String> inputData) {
        if (inputData.containsValue("")) return new ArrayList<>();
        String pattern = String.join(" ", inputData.values());
        List<String> bookList = bookService.getCatalogueWithFilter(inputData.keySet());
        if (modelData.isSuggestAllSimilarOptions()) {
            long time = System.currentTimeMillis();
            List<String> result = getBooksWithSoftFilters(bookList, pattern);
            System.out.println(System.currentTimeMillis() - time);
            return result;
        } else {
            return List.of(getBookWithStrongFilters(bookList, pattern));
        }
    }

    private String getBookWithStrongFilters(List<String> bookList, String pattern) {
        String result = "";
        int maxScore = 0;
        for (String book : bookList) {
            int score = 0;
            for (String subPattern : pattern.split(" ")) {
                if (book.toLowerCase().contains(subPattern.toLowerCase().strip())) {
                    score++;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                result = book;
            }
        }
        return result;
    }

    private ArrayList<String> getBooksWithSoftFilters(List<String> bookList, String pattern) {
        String[] splittedPattern = pattern.split(" ");

        Map<String, Map<Integer, Integer>> booksPriorities = new HashMap<>();

        for (String book : bookList) {
            Map<Integer, Integer> priorityMap = new HashMap<>();
            for (String patternPart : splittedPattern) {
                String patternPartFromEndCopy, patternPartFromStartCopy;
                int patternPartLength = patternPart.length();

                int i = 0;
                int searchDepth = patternPart.length() / 2;
                do {
                    patternPartFromEndCopy = patternPart.toLowerCase().substring(0, patternPartLength - i);
                    patternPartFromStartCopy = patternPart.toLowerCase().substring(i);
                    if (patternPartFromEndCopy.equals("") || patternPartFromStartCopy.equals("")
                            || i == searchDepth) break;

                    int countOfIntersects = !patternPartFromStartCopy.equals(patternPartFromEndCopy)
                            ? KMP(book.toLowerCase(), patternPartFromStartCopy) + KMP(book.toLowerCase(), patternPartFromEndCopy)
                            : KMP(book.toLowerCase(), patternPartFromStartCopy);

                    priorityMap.put(i, priorityMap.getOrDefault(i, 0) + countOfIntersects);

                    i++;
                } while (true);
            }

            booksPriorities.put(book, priorityMap);
        }

        return getSortedByWeightBooks(booksPriorities);
    }


    private ArrayList<String> getSortedByWeightBooks(Map<String, Map<Integer, Integer>> booksPriorities) {
        List<Map<Integer, Integer>> list = new ArrayList<>(booksPriorities.values());
        checkValidity(list);
        if (list.size() == 0) return new ArrayList<>();

        Comparator<Map<Integer, Integer>> comparator = (o1, o2) -> {
            for (int i = 0; i < o1.keySet().size(); i++) {
                if (!o1.get(i).equals(o2.get(i))) {
                    return o2.get(i).compareTo(o1.get(i));
                }
            }
            return 0;
        };
        list.sort(comparator);

        Map<String, Map<Integer, Integer>> result = new LinkedHashMap<>();
        for (Map<Integer, Integer> sortedMap : list) {
            for (Map.Entry<String, Map<Integer, Integer>> mapPair : booksPriorities.entrySet()) {
                if (mapPair.getValue().equals(sortedMap)) {
                    result.put(mapPair.getKey(), mapPair.getValue());
                }
            }
        }

        return new ArrayList<>(result.keySet());
    }

    private void checkValidity(List<Map<Integer, Integer>> list) {
        list.removeIf(map -> new HashSet<>(map.values()).size() == 1);
    }

    public static int KMP(String text, String pattern) {
        int countOfOccurs = 0;
        // базовый случай 1: шаблон нулевой или пустой
        if (pattern == null || pattern.length() == 0) {
            return -1;
        }

        // базовый случай 2: текст равен NULL или длина текста меньше длины шаблона
        if (text == null || pattern.length() > text.length()) {
            return -1;
        }

        char[] chars = pattern.toCharArray();

        // next[i] сохраняет индекс следующего лучшего частичного совпадения
        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++) {
            int j = next[i];

            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }

            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }

        for (int i = 0, j = 0; i < text.length(); i++) {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j)) {
                if (++j == pattern.length()) {
                    countOfOccurs++;
                }
            } else if (j > 0) {
                j = next[j];
                i--;    // так как `i` будет увеличен на следующей итерации
            }
        }
        return countOfOccurs;
    }
}
