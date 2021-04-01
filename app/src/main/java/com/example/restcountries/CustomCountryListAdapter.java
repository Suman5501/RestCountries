package com.example.restcountries;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.restcountries.db.Country;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class CustomCountryListAdapter extends RecyclerView.Adapter<CustomCountryListAdapter.ViewHolder> {

    Context context;
    List<Country> arrayList;

    public CustomCountryListAdapter(Context context, List<Country> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Country current = arrayList.get(position);

//        holder.name.setText(repo.getName());
//        holder.chef.setText("By " + repo.getChef());
//        holder.description.setText(repo.getDescription());
//        holder.price.setText("Price: ₹" + repo.getPrice());
//        holder.timestamp.setText(repo.getTimestamp());
//
//        Glide.with(context)
//                .load(repo.getThumbnail())
//                .into(holder.thumbnail);


        holder.name.setText(current.getName());
        holder.capital.setText(current.getCapital());
        holder.region.setText(current.getRegion());
        holder.subregion.setText(current.getSubregion());
        holder.population.setText(current.getPopulation());
        holder.borders.setText(current.getBorders());
        holder.languages.setText(current.getLanguages());

        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                .init()
                .with(context)
                .getRequestBuilder();
        requestBuilder
                .load(current.getFlag())
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                        .centerCrop())
                .into(holder.flag);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name ,capital, region, subregion, population, borders, languages;
        public ImageView flag;

        public ViewHolder(View itemView) {
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