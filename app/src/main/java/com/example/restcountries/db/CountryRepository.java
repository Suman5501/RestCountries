package com.example.restcountries.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


class CountryRepository {

    private CountryDAO mCountryDao;
    private LiveData<List<Country>> mAllCountries;

    CountryRepository(Application application) {
        CountryRoomDatabase db = CountryRoomDatabase.getDatabase(application);
        mCountryDao = db.countryDao();
        mAllCountries = mCountryDao.getAllCountries();
    }


    LiveData<List<Country>> getAllCountries() {
        return mAllCountries;
    }


    void insert(Country country) {
        CountryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCountryDao.insert(country);
        });
    }

    void delete(Country country) {
        CountryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCountryDao.delete(country);
        });
    }

    public void deleteAll(Country country) {
        CountryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCountryDao.deleteAll();
        });
    }
}
//public class CountryRepository {


//
//    private CountryDAO mCountryDao;
//    private LiveData<List<Country>> mAllCountries;
//
//    public CountryRepository(Application application) {
//        CountryRoomDatabase db = CountryRoomDatabase.getInstance(application);
//        mCountryDao = db.countryDao();
//        mAllCountries = mCountryDao.getAllCountries();
//    }
//
//    LiveData<List<Country>> getAllCountries() {
//        return mAllCountries;
//    }
//
//    public void insert (Country country) {
//        new insertAsyncTask(mCountryDao).execute(country);
//    }
//
//    public void delete(Country country){
//        new deleteAsyncTask(mCountryDao).execute(country);
//    }
//
//    public void deleteAll(Country country){
//        new deleteAllAsyncTask(mCountryDao).execute(country);
//    }
//
//    private static class insertAsyncTask extends AsyncTask<Country, Void, Void> {
//
//        private CountryDAO mAsyncTaskDao;
//
//        private insertAsyncTask(CountryDAO countryDao) {
//            this.mAsyncTaskDao = countryDao;
//        }
//
//        @Override
//        protected Void doInBackground(final Country... params) {
//            mAsyncTaskDao.insert(params[0]);
//            return null;
//        }
//    }
//
//
//    private static class deleteAsyncTask extends AsyncTask<Country, Void, Void> {
//
//        private CountryDAO mAsyncTaskDao;
//
//        private deleteAsyncTask(CountryDAO countryDao) {
//            this.mAsyncTaskDao = countryDao;
//        }
//
//        @Override
//        protected Void doInBackground(final Country... params) {
//            mAsyncTaskDao.delete(params[0]);
//            return null;
//        }
//    }
//
//
//    private static class deleteAllAsyncTask extends AsyncTask<Country, Void, Void> {
//
//        private CountryDAO mAsyncTaskDao;
//
//        private deleteAllAsyncTask(CountryDAO countryDao) {
//            this.mAsyncTaskDao = countryDao;
//        }
//
//        @Override
//        protected Void doInBackground(final Country... params) {
//            mAsyncTaskDao.deleteAll();
//            return null;
//        }
//    }
//}