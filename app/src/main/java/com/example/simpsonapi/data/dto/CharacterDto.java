package com.example.simpsonapi.data.dto;

import com.google.gson.annotations.SerializedName;

public class CharacterDto {
    @SerializedName("quote")
    private String quote;

    @SerializedName("character")
    private String character;

    @SerializedName("image")
    private String img;

    @SerializedName("characterDirection")
    private String characterDirection;

    public CharacterDto(String quote, String character, String img, String characterDirection) {
        this.quote = quote;
        this.character = character;
        this.img = img;
        this.characterDirection = characterDirection;
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
