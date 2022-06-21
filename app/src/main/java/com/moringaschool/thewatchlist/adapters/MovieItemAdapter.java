package com.moringaschool.thewatchlist.adapters;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.thewatchlist.R;
import com.moringaschool.thewatchlist.models.Result;
import com.moringaschool.thewatchlist.ui.MovieReview;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.viewHolder> {
    List<Result> list;
    Context context;
    private View view;

    public MovieItemAdapter(List<Result> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dispalyitem, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
     Picasso.get().load(list.get(position).getMultimedia().getSrc()).into(holder.imageView);
             holder.nameView.setText(list.get(position).getDisplayTitle());
     holder.summaryShort.setText(list.get(position).getSummaryShort());
     holder.card.setOnClickListener(v->{
         MoviePagerAdapter.position = holder.getAdapterPosition();
         Intent intent = new Intent(context, MovieReview.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(intent);
     });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.idIVCourseImage)
        ImageView imageView;
        @BindView(R.id.idTVCourseName)
        TextView nameView;
        @BindView(R.id.idTVCourseSummary)
        TextView summaryShort;
        @BindView(R.id.cardview)
        CardView card;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,view);

        }
    }
}
