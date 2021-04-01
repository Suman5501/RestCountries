package com.example.restcountries.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CountryDAO {

    @Query("SELECT * FROM country_table ORDER BY name ASC")
    LiveData<List<Country>> getAllCountries();


    @Insert
    void insertAll(Country... countries);

    @Delete
    void delete(Country country);

    @Query("DELETE FROM country_table")
    void deleteAll();

    @Insert
    void insert(Country country);
}
