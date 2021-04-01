package com.example.restcountries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.restcountries.db.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM country_table")
    List<Country> getAll();

    @Insert
    void insert(Country country);

}