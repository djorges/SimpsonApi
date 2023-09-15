package com.example.simpsonapi.domain;


public class Character extends Quote {
    private String img;

    private String characterDirection;

    public Character( String quote, String character, String img, String characterDirection) {
        super(quote, character);
        this.img = img;
        this.characterDirection = characterDirection;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCharacterDirection() {
        return characterDirection;
    }

    public void setCharacterDirection(String characterDirection) {
        this.characterDirection = characterDirection;
    }
}
