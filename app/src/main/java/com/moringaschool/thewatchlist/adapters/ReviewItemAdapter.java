package com.moringaschool.thewatchlist.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.moringaschool.thewatchlist.Constants;
import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.models.Reviews;
import com.moringaschool.thewatchlist.ui.MovieReview;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewItemAdapter extends RecyclerView.Adapter<ReviewItemAdapter.viewHolder> {
    List<Reviews> list = new ArrayList<Reviews>();
    Context context;
    private View view;

    public ReviewItemAdapter(List<Reviews> list, Context context) {
        this.list = list;
        this.context =context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dispaly_review, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

//        if(list==null){
//            list = Constants.RESULTS_RESTORE;
//        }
        if (
                list.get(position).getUserImage()!= null) {
            Picasso.get().load(list.get(position).getUserImage()).into(holder.imageView);
        }
        holder.nameView.setText(list.get(position).getUser());
        holder.summaryShort.setText(list.get(position).getReview());
       holder.ratingBar.setRating(list.get(position).getRating());
//        holder.card.setOnClickListener(v -> {
//            MoviePagerAdapter.position = holder.getAdapterPosition();
//            Intent intent = new Intent(context, MovieReview.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.idIVCourseImage)
        ShapeableImageView imageView;
        @BindView(R.id.idTVCourseName)
        TextView nameView;
        @BindView(R.id.idTVCourseSummary)
        TextView summaryShort;
        @BindView(R.id.cardview)
        CardView card;
        @BindView(R.id.ratingBar2)
        RatingBar ratingBar;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);

        }
    }
}
