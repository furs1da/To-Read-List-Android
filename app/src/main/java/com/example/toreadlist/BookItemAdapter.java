package com.example.toreadlist;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import com.squareup.picasso.Picasso;

import static com.example.toreadlist.BookValues.*;

public class BookItemAdapter extends RecyclerView.Adapter<BookItemViewHolder>{

    private ArrayList<BookItem> bookItemArrayList;
    private Context appContext;


    public BookItemAdapter(ArrayList<BookItem> bookItemArrayList, Context appContext) {
        this.bookItemArrayList = bookItemArrayList;
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public BookItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_db_item, parent, false);
        return new BookItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookItemViewHolder holder, int position) {

        BookItem bookItem = bookItemArrayList.get(position);


        holder.titleTV.setText(bookItem.getTitle());
        holder.publisherTV.setText(bookItem.getPublisher());
        holder.pageCountTV.setText("# of Pages : " + bookItem.getPageCount());
        holder.isFinishedTV.setText(bookItem.getIsCompleted() == true ? "Finished" : "In Process");
        holder.dateTV.setText(bookItem.getPublishedDate());

        try{
            holder.languageTV.setText(language_codes.get(bookItem.getLanguage()));
        }
        catch(Exception ex)
        {
            holder.languageTV.setText(bookItem.getLanguage());
        }

        Picasso.get().load(bookItem.getImageUrl()).into(holder.bookIV);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(appContext, BookFetchDetails.class);
                i.putExtra("title", bookItem.getTitle());

                try{
                    i.putExtra("language", language_codes.get(bookItem.getLanguage()));
                }
                catch(Exception ex)
                {
                    i.putExtra("language", bookItem.getLanguage());
                }


                i.putExtra("publisher", bookItem.getPublisher());
                i.putExtra("publishedDate", bookItem.getPublishedDate());
                i.putExtra("description", bookItem.getDescription());
                i.putExtra("pageCount", bookItem.getPageCount());
                i.putExtra("imageUrl", bookItem.getImageUrl());
                i.putExtra("isCompleted", bookItem.getIsCompleted());
                i.putExtra("id", bookItem.getId());
                i.putExtra("bookDetailsLink", bookItem.getBookDetailsLink());

                appContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookItemArrayList.size();
    }
}

