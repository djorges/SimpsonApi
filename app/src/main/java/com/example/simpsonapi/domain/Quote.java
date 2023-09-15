package com.example.simpsonapi.domain;

public class Quote {

    private String quote;

    private String character;

    public Quote(String quote, String character) {
        this.quote = quote;
        this.character = character;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
