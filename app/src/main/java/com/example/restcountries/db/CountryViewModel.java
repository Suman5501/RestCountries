package com.example.restcountries.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository mRepository;
    private LiveData<List<Country>> mAllCountries;

    public CountryViewModel (Application application) {
        super(application);
        mRepository = new CountryRepository(application);
        mAllCountries = mRepository.getAllCountries();
    }
//
    public LiveData<List<Country>> getAllCountries() { return mAllCountries; }
//
    public void insert(Country country) { mRepository.insert(country); }
//
    public void delete(Country country) {mRepository.delete(country);}
//
    public void deleteAll(Country country) {mRepository.deleteAll(country);}
//
//}

}