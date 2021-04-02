package com.example.restcountries.db;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.restcountries.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private final LayoutInflater mInflater;
    private Context mcontext;
    private List<Country> mCountries = new ArrayList<>(); // Cached copy of words

    public CountryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mcontext = context;

    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.country_list_item, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        if (mCountries != null) {
            Country current = mCountries.get(position);

            holder.name.setText(current.getName());
            holder.capital.setText(current.getCapital());
            holder.region.setText(current.getRegion());
            holder.subregion.setText(current.getSubregion());
            holder.population.setText(current.getPopulation());
            holder.borders.setText(current.getBorders());
            holder.languages.setText(current.getLanguages());

            RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                    .init()
                    .with(mcontext)
                    .getRequestBuilder();
            requestBuilder
                    .load(current.getFlag())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.flag);
        } else {

            holder.name.setText("No Country");
        }
    }

    public void update(List<Country> countries) {
//        this.mCountries = countries;
        mCountries.clear();
        mCountries.addAll(countries);
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCountries != null)
            return mCountries.size();
        else return 0;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        private final TextView name ,capital, region, subregion, population, borders, languages;
        private final ImageView flag;

        private CountryViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            flag = itemView.findViewById(R.id.news_image);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subregion = itemView.findViewById(R.id.subregion);
            population = itemView.findViewById(R.id.population);
            borders = itemView.findViewById(R.id.borders);
            languages = itemView.findViewById(R.id.language);
        }
    }
}
