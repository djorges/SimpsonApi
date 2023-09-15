package com.example.simpsonapi.data.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "quotes")
public class QuoteEntity {
    @PrimaryKey()
    @NonNull
    private String quote;

    @ColumnInfo(name = "name")
    private String character;

    public QuoteEntity(@NonNull String quote, String character) {
        this.quote = quote;
        this.character = character;
    }

    @NonNull
    public String getQuote() {
        return quote;
    }

    public void setQuote(@NonNull String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
