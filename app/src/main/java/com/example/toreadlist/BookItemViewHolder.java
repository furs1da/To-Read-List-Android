package com.example.toreadlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BookItemViewHolder extends RecyclerView.ViewHolder {

    TextView titleTV, publisherTV, pageCountTV, isFinishedTV, dateTV, languageTV;
    ImageView bookIV;

    public BookItemViewHolder(View itemView) {
        super(itemView);
        titleTV = itemView.findViewById(R.id.bookItemTitleTV);
        publisherTV = itemView.findViewById(R.id.bookItemPublisherTV);
        pageCountTV = itemView.findViewById(R.id.bookItemPageCountTV);
        isFinishedTV = itemView.findViewById(R.id.bookItemFinishedTV);
        dateTV = itemView.findViewById(R.id.bookItemLanguageTV);
        languageTV = itemView.findViewById(R.id.bookItemLanguageTV);
        bookIV = itemView.findViewById(R.id.bookItemImageIV);
    }

}
