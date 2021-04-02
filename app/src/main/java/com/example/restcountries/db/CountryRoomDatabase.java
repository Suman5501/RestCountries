package com.example.restcountries.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Country.class}, version = 1,exportSchema = false)
public abstract class CountryRoomDatabase extends RoomDatabase {

    public abstract CountryDAO countryDao();

    private static volatile CountryRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static CountryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CountryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryRoomDatabase.class, "country_database")
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
//
//        @Override
//        public void onOpen (@NonNull SupportSQLiteDatabase db){
//            super.onOpen(db);
//            // If you want to keep the data through app restarts,
//            // comment out the following line.
//            new PopulateDbAsync(INSTANCE).execute();
//        }
//    };


//    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
//
//        private final CountryDAO mDao;
//
//
//
//        PopulateDbAsync(CountryRoomDatabase db) {
//            mDao = db.countryDao();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate on creation.
//
//            ArrayList<Country> countryData = new ArrayList<>();
//            Country country = new Country("name","capital","flag","region","subregion","988774567","pak,china","eng");
//            countryData.add(country);
//
//
//                for(int i=0;i<countryData.size();i++){
//                    mDao.insert(countryData.get(i));
//                }
//                return null;
//        }
//    }
}
