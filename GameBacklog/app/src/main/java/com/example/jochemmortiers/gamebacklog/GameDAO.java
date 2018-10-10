package com.example.jochemmortiers.gamebacklog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GameDAO {

    @Insert
    void addGame(Game movies);

    @Query("SELECT * FROM Game")
    List<Game> getAllGames();

    @Update
    void updateGame(Game pGame);

    @Delete
    void deleteGame(Game pGame);
}
