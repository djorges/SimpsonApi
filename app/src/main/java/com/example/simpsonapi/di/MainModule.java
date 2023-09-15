package com.example.simpsonapi.di;


import android.content.ClipboardManager;
import android.content.Context;

import androidx.room.Room;

import com.example.simpsonapi.data.api.SimpsonApi;
import com.example.simpsonapi.data.db.CharacterDatabase;
import com.example.simpsonapi.data.db.QuoteDao;
import com.example.simpsonapi.domain.Mappers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class MainModule{
    @Provides
    @Singleton
    static SimpsonApi provideSimpsonApi(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SimpsonApi.class);
    }

    @Provides
    @Singleton
    static CharacterDatabase provideCharacterDatabase(
            @ApplicationContext Context context
    ){
        return Room.databaseBuilder(
            context,
            CharacterDatabase.class,
            DB_NAME
        )
        .build();
    }

    @Provides
    @Singleton
    static QuoteDao provideCharacterDao(
            CharacterDatabase db
    ){
        return db.quoteDao();
    }

    @Provides
    @Singleton
    static Mappers provideMappers(){
        return new Mappers();
    }

    @Provides
    @Singleton
    static ClipboardManager provideClipboardService(@ApplicationContext Context context){
        return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    private static final String BASE_URL = "https://thesimpsonsquoteapi.glitch.me/";
    private static final String DB_NAME = "characters-db";
}
