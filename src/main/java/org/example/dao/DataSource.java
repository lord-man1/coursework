package org.example.dao;

import org.example.bean.Book;

import java.util.Arrays;
import java.util.List;

public class DataSource {
    private final List<Book> bookList;

    {
        Book book1 = new Book("Горе от ума", "Александр Сергеевич Грибоедов", "Главным замыслом произведения является иллюстрация подлости, " +
                "невежества и раболепия перед чинами и традициями, противостояли которым новые идеи, подлинная культура, свобода и разум.");
        Book book2 = new Book("Евгений Онегин", "Александр Сергеевич Пушкин", "Это одно из самых гармоничных по композиции и богатых по содержанию произведений Пушкина. " +
                "Благодаря выражению эмоций героев посредством стихотворной формы, роман получает большую лиричность и выразительность, " +
                "таким образом, читателю становится понятна и доступна вся палитра чувств, которую закладывал в основу автор.");
        Book book3 = new Book("Герой нашего времени", "Михаил Юрьевич Лермонтов", "");
        Book book4 = new Book("Мёртвые души", "Николай Васильевич Гоголь", "");
        bookList = Arrays.asList(book1, book2, book3, book4);
    }

    private DataSource() {
    }

    private static final class SingletonHolder {
        public static final DataSource INSTANCE_HOLDER = new DataSource();
    }

    public static DataSource getInstance() {
        return SingletonHolder.INSTANCE_HOLDER;
    }

    public List<Book> getCatalogue() {
        return bookList;
    }
}
