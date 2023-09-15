package com.example.simpsonapi.data.repository;

import static java.util.Collections.emptyList;

import android.util.Log;

import com.example.simpsonapi.data.api.SimpsonApi;
import com.example.simpsonapi.data.db.QuoteDao;
import com.example.simpsonapi.data.db.QuoteEntity;
import com.example.simpsonapi.data.service.ClipboardService;
import com.example.simpsonapi.domain.CharactersRepository;
import com.example.simpsonapi.domain.Mappers;
import com.example.simpsonapi.domain.Character;
import com.example.simpsonapi.domain.Quote;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CharactersRepositoryImpl implements CharactersRepository {
    private final SimpsonApi simpsonApi;
    private final QuoteDao dao;
    private final ClipboardService clipboardService;
    private Mappers mappers;
    @Inject
    public CharactersRepositoryImpl(
            ClipboardService clipboardService,
            SimpsonApi simpsonApi,
            QuoteDao dao,
            Mappers mappers
    ) {
        this.simpsonApi = simpsonApi;
        this.clipboardService = clipboardService;
        this.mappers = mappers;
        this.dao = dao;
    }

    @Override
    public Observable<List<Character>> fetchCharacters() {
        return simpsonApi.fetchCharacters()
                .flatMapIterable( list -> list)
                .map(item -> mappers.dtoToModel(item))
                .toList()
                .toObservable();
    }

    @Override
    public Observable<List<Character>> fetchCharacter(String name) {
        return simpsonApi.fetchCharacter(name)
                .flatMapIterable( list -> list)
                .map(item -> mappers.dtoToModel(item))
                .toList()
                .toObservable();
    }

    @Override
    public Maybe<Long> insertQuote(Quote quote) {
        return dao.insert(mappers.modelToEntity(quote));
    }

    @Override
    public Completable deleteQuote(Quote quote) {
        return dao.delete(mappers.modelToEntity(quote));
    }

    @Override
    public Observable<List<Quote>> getQuotes(){
        return dao.getAll().map(list ->
            list.stream().map(item-> mappers.entityToModel(item)).collect(Collectors.toList())
        );
    }

    @Override
    public void copyQuoteIntoClipboard(String quote) {
        clipboardService.copyQuoteToClipboard(quote);
    }
}