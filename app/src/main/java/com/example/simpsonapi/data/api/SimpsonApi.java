package com.example.simpsonapi.data.api;


import com.example.simpsonapi.data.dto.CharacterDto;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SimpsonApi {
    @GET("quotes?count=12")
    Observable<List<CharacterDto>> fetchCharacters();

    @GET("quotes")
    Observable<List<CharacterDto>> fetchCharacter(
        @Query("character") String character
    );
}