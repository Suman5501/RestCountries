package com.example.restcountries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.example.restcountries.db.Country;
import com.example.restcountries.db.CountryDAO;
import com.example.restcountries.db.CountryListAdapter;

import com.example.restcountries.db.CountryRoomDatabase;
import com.example.restcountries.db.CountryViewModel;
import com.example.restcountries.models.MySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryInfoActivity extends AppCompatActivity {

//    private CountryViewModel mCountryViewModel;
//    private GoogleSignInClient mGoogleSignInClient;

//    private CountryRoomDatabase db;
//    GoogleApiClient googleApiClient;
//
    private CountryDAO mDao;


    private static String URL = "https://restcountries.eu/rest/v2/region/asia";
    private RecyclerView recyclerview;

    private List<CountryData> countries;
    private CustomCountryListAdapter adapter;
    private ProgressBar pb;
    private FloatingActionButton mFloatingActionButton;
    private CountryViewModel mCountryViewModel;
    private CountryRoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.GONE);

        recyclerview = findViewById(R.id.countryRecyclerView);
        countries = new ArrayList<>();
//        countries = fetchData();



        mFloatingActionButton = findViewById(R.id.floatingActionButton);


//         mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
////                 mDao.deleteAll();
////                 db.countryDao().deleteAll();
//             }
//         });
//
//

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            fetchData();
        } else {

            fetchfromRoom();
        }


    }


    private void fetchfromRoom() {


        Toast.makeText(this,"Network connection failed displaying from room",Toast.LENGTH_SHORT).show();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        final CountryListAdapter listadapter = new CountryListAdapter(this);
        recyclerview.setAdapter(listadapter);

        mCountryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        mCountryViewModel.getAllCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable final List<Country> countries) {
                // Update the cached copy of the words in the adapter.
                listadapter.setCountries(countries);
            }
        });

    }


//        mFloatingActionButton = findViewById(R.id.floatingActionButton);
//        QueryUtils queryUtils = new QueryUtils(getApplicationContext());
//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        googleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.countryRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//        final CountryListAdapter listadapter = new CountryListAdapter(this);
//        recyclerView.setAdapter(listadapter);
//
//         mCountryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
//
//         mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
////                 mDao.deleteAll();
////                 db.countryDao().deleteAll();
//             }
//         });
//
//
//        mCountryViewModel.getAllCountries().observe(this, new Observer<List<Country>>() {
//            @Override
//            public void onChanged(@Nullable final List<Country> countries) {
//                // Update the cached copy of the words in the adapter.
//                listadapter.setCountries(countries);
//            }
//        });

//    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.sign_out_menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.sign_out_menu:
//                signOut();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(CountryInfoActivity.this,"Sign out",Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

//    public  extractCountry() {

    private void fetchData(){

        String url = "https://restcountries.eu/rest/v2/region/asia";
        List<Country> countryData = new ArrayList<>();

//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        RequestQueue queue = MySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
//                        final int numberOfItemsInResp = response.length();

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject currentCountry = response.getJSONObject(i);

                        String name = currentCountry.getString("name");
                        String capital = currentCountry.getString("capital");
                        String flag = currentCountry.getString("flag");
                        String region = currentCountry.getString("region");
                        String subregion = currentCountry.getString("subregion");
                        String population = currentCountry.getString("population");

                        JSONArray borders = currentCountry.getJSONArray("borders");

                        String[] list = new String[borders.length()];
                        for (int j = 0; j < borders.length(); j++) {
                            list[j] = (borders.getString(j));
                        }

                        String bordersValue = Arrays.toString(list).replace(" ", " ,");

                        JSONArray langs = currentCountry.getJSONArray("languages");
                        String[] array = new String[langs.length()];

                        String langsValue = "";
                        for (int j = 0; j < langs.length(); j++) {
                            JSONObject currentLang = langs.getJSONObject(j);
                            String langName = currentLang.getString("name");
                            langsValue += " " + langName;

                        }

                        Country data = new Country(name, capital, flag, region, subregion, population, bordersValue, langsValue);
                        countryData.add(data);

                    }
                    recyclerview.setHasFixedSize(true);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new CustomCountryListAdapter(CountryInfoActivity.this, countryData);
                    recyclerview.setAdapter(adapter);

                } catch (JSONException e) {
                    // If an error is thrown when executing any of the above statements in the "try" block,
                    // catch the exception here, so the app doesn't crash. Print a log message
                    // with the message from the exception.
                    Log.e("Error:", "Problem parsing the earthquake JSON results", e);
                }
                saveTask(countryData);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

//        queue.add(jsonArrayRequest);
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


    }

private void saveTask(List<Country> countryData){

    mCountryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

    class SaveTask extends AsyncTask<Void, Void, Void>{


            @Override
            protected Void doInBackground(final Void... params) {

            mDao.deleteAll();
            for(int i=0;i<countryData.size();i++){
                mDao.insert(countryData.get(i));
//                mCountryViewModel.insert(countryData.get(i));

            }
            return null;
        }
    }

//    db = CountryRoomDatabase.getInstance(getApplicationContext());
    SaveTask st = new SaveTask();
    st.execute();


    }
}


