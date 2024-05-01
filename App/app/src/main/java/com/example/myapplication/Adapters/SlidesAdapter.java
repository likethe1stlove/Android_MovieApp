package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Domain.SlideItems;
import com.example.myapplication.R;


import java.util.List;

public class SlidesAdapter extends RecyclerView.Adapter<SlidesAdapter.SliderViewholder> {
    private List<SlideItems> slideItems;
    private ViewPager2 viewPager2;
    private Context context;
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            slideItems.addAll(slideItems);
            notifyDataSetChanged();
        }
    };

    public SlidesAdapter(List<SlideItems> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlidesAdapter.SliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();

        return new SliderViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlidesAdapter.SliderViewholder holder, int position) {
    holder.setImage(slideItems.get(position));
    if(position==slideItems.size()-2){
        viewPager2.post(runnable);
    }
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }


    public class SliderViewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView nameTxt,genreTxt,ageTxt,yearTxt,timeTxt;
        public SliderViewholder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageSlide);
            nameTxt=itemView.findViewById(R.id.nameTxt);
            genreTxt=itemView.findViewById(R.id.genreTxt);
            ageTxt=itemView.findViewById(R.id.ageTxt);
            yearTxt=itemView.findViewById(R.id.yearTxt);
            timeTxt=itemView.findViewById(R.id.timeTxt);
        }

        void setImage(SlideItems slideItems){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(60));
            Glide.with(context)
                    .load(slideItems.getImage())
                    .apply(requestOptions)
                    .into(imageView);
            nameTxt.setText(slideItems.getName());
            genreTxt.setText(slideItems.getGenre());
            ageTxt.setText(slideItems.getAge());
            yearTxt.setText(""+slideItems.getYear());
            timeTxt.setText(slideItems.getTime());
        }

    }
}
