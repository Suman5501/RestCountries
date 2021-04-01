package com.example.restcountries.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//(tableName = "country_table")
//remove seriaziable
@Entity (tableName = "country_table")
public class Country implements Serializable {

    @PrimaryKey
    @NonNull
    private String name;

    private String capital;
    private String flag;
    private String region;
    private String subregion;
    private String population;
    private String borders;
    private String languages;


    public Country(@NonNull String name, String capital, String flag, String region, String subregion, String population, String borders, String languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getPopulation() {
        return population;
    }

    public String getBorders() {
        return borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}

