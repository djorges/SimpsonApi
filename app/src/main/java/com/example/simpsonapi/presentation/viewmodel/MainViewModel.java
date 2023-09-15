package com.example.simpsonapi.presentation.viewmodel;

import static java.util.Collections.emptyList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simpsonapi.domain.Character;
import com.example.simpsonapi.domain.CharactersRepository;
import com.example.simpsonapi.domain.Quote;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private CharactersRepository repository;
    private MutableLiveData<List<Character>> characterList;
    public MutableLiveData<List<Character>> getCharacterList(){
        if(characterList == null)
        {
            characterList = new MutableLiveData<>();
        }
        return characterList;
    }

    private MutableLiveData<List<Quote>> quoteList;
    public MutableLiveData<List<Quote>> getQuoteList(){
        if(quoteList == null)
        {
            quoteList = new MutableLiveData<>();
        }
        return quoteList;
    }
    private final Observer<List<Character>> observeCharacters = new Observer<List<Character>>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull List<Character> characters) {
            characterList.postValue(characters);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            e.printStackTrace();
            characterList.postValue(emptyList());
        }

        @Override
        public void onComplete() {

        }
    };

    @Inject
    MainViewModel(CharactersRepository repository){
        this.repository = repository;
    }

    public void fetchAllCharacters(){
        repository.fetchCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observeCharacters);
    }

    public void fetchCharacterQuote(String characterName){
        repository.fetchCharacter(characterName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observeCharacters);
    }

    public Maybe<Long> insertQuote(String quote, String name){
        return repository.insertQuote(new Quote(quote,name));
    }
    public Completable deleteQuote(Quote quote){
        return repository.deleteQuote(quote);
    }

    public Observable<List<Quote>> getAllQuotes(){
        return repository.getQuotes();
    }

    public void copyQuoteIntoClipboard(String quote){
        repository.copyQuoteIntoClipboard(quote);
    }
}
