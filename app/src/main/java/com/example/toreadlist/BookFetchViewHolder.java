package com.example.toreadlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BookFetchViewHolder extends RecyclerView.ViewHolder  {
    TextView titleTV, publisherTV, pageCountTV, averageRatingTV, dateTV;
    ImageView bookIV;

    public BookFetchViewHolder(View itemView) {
        super(itemView);
        titleTV = itemView.findViewById(R.id.bookTitleTV);
        publisherTV = itemView.findViewById(R.id.bookPublisherTV);
        pageCountTV = itemView.findViewById(R.id.bookPageCountTV);
        averageRatingTV = itemView.findViewById(R.id.bookAverageRatingTV);
        dateTV = itemView.findViewById(R.id.bookPublishedDateTV);
        bookIV = itemView.findViewById(R.id.bookImageIV);
    }
}
