package com.example.simpsonapi.domain;

import com.example.simpsonapi.data.db.QuoteEntity;
import com.example.simpsonapi.data.dto.CharacterDto;

import javax.inject.Inject;

public class Mappers{
    @Inject
    public Mappers() {}

    public Character dtoToModel(CharacterDto dto){
        return new Character(
                dto.getQuote(),
                dto.getCharacter(),
                dto.getImg(),
                dto.getCharacterDirection()
        );
    }

    public Quote entityToModel(QuoteEntity entity){
        return new Quote(
            entity.getQuote(),
            entity.getCharacter()
        );
    }

    public QuoteEntity modelToEntity(Quote model){
        return new QuoteEntity(
            model.getQuote(),
            model.getCharacter()
        );
    }
}

