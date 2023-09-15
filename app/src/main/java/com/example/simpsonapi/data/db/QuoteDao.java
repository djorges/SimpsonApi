package com.example.simpsonapi.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface QuoteDao {
    @Query("SELECT * FROM quotes")
    Observable<List<QuoteEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Maybe<Long> insert(QuoteEntity entity);

    @Query("SELECT * FROM quotes ORDER BY quote DESC")
    Observable<List<QuoteEntity>> getAllDescOrder();

    @Delete
    Completable delete(QuoteEntity entity);
}
