package com.example.simpsonapi.di;

import com.example.simpsonapi.domain.CharactersRepository;
import com.example.simpsonapi.data.repository.CharactersRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

  @Binds
  public abstract CharactersRepository bindCharactersRepository(
        CharactersRepositoryImpl charactersRepositoryImpl
  );
}