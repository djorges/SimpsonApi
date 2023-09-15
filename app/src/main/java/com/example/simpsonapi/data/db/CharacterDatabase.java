package com.example.simpsonapi.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
    entities = {QuoteEntity.class},
    version = 1
)
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract QuoteDao quoteDao();
}