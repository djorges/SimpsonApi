package com.example.simpsonapi.domain;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

public interface CharactersRepository {
    Observable<List<Character>> fetchCharacters();
    Observable<List<Character>> fetchCharacter(String name);

    Maybe<Long> insertQuote(Quote quote);

    Completable deleteQuote(Quote quote);

    Observable<List<Quote>> getQuotes();

    void copyQuoteIntoClipboard(String quote);
}