package com.example.toreadlist;

import java.util.HashSet;
import java.util.Hashtable;

public final class BookValues {

    static final Hashtable<String, String> language_codes = new Hashtable<String, String>(){
        {
            put("en", "English");
            put("it", "Italian");
            put("es", "Spanish");
            put("ru", "Russian");
            put("uk", "Ukrainian");
            put("pt-PT", "Portuguese");
            put("pt-BR", "Portuguese ");
            put("ar", "Arabic");
            put("hi", "Hindi");
            put("ja", "Japanese");
        }
    };

}
