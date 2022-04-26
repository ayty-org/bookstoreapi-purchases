package br.com.bookstoreapi.purchases.builders;


import br.com.bookstoreapi.purchases.book.Category;

import java.util.LinkedList;
import java.util.List;

public class CategoryBuilder {

    public static Category categoryRomance(){
        return Category.builder()
                .id(1L)
                .name("Romance")
                .build();
    }
    public static Category categoryComedy(){
        return Category.builder()
                .id(2L)
                .name("Comedy")
                .build();
    }
    public static Category categoryAdventure(){
        return Category.builder()
                .id(3L)
                .name("Adventure")
                .build();
    }

    public static List<Category> categoryList(){
        List<Category> categories = new LinkedList<>();
        categories.add(categoryRomance());
        categories.add(categoryComedy());
        categories.add(categoryAdventure());
        return categories;
    }


}
