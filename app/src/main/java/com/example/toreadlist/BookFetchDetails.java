package com.example.toreadlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.toreadlist.BookValues.*;

public class BookFetchDetails extends AppCompatActivity {
    String title, subtitle, publisher, publishedDate, description, thumbnail, previewLink, infoLink, buyLink, language;
    int pageCount;
    private ArrayList<String> authors;

    TextView titleTV, publisherTV, descTV, pageTV, publishDateTV, languageTV;
    Button buyBtn;
    private ImageView bookIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_fetch_details);
        getSupportActionBar().hide();

        titleTV = findViewById(R.id.bookDetailsTitleTV);
        publisherTV = findViewById(R.id.bookDetailsPublisherTV);
        descTV = findViewById(R.id.bookDetailsDescriptionTV);
        pageTV = findViewById(R.id.bookDetailsNoPagesTV);
        publishDateTV = findViewById(R.id.bookDetailsPublishDateTV);
        languageTV = findViewById(R.id.bookDetailsLanguageTV);
        buyBtn = findViewById(R.id.bookDetailsBuyBtn);
        bookIV = findViewById(R.id.bookDetailsIV);


        title = getIntent().getStringExtra("title");
        publisher = getIntent().getStringExtra("publisher");
        publishedDate = getIntent().getStringExtra("publishedDate");
        description = getIntent().getStringExtra("description");
        pageCount = getIntent().getIntExtra("pageCount", 0);
        thumbnail = getIntent().getStringExtra("imageUrl");
        previewLink = getIntent().getStringExtra("previewLink");
        infoLink = getIntent().getStringExtra("bookDetailsLink");
        buyLink = getIntent().getStringExtra("buyLink");
        language = getIntent().getStringExtra("language");


        titleTV.setText(title);
        publisherTV.setText(publisher);
        publishDateTV.setText("Published On : " + publishedDate);
        descTV.setText(description);
        languageTV.setText(language);

        pageTV.setText("# Of Pages : " + pageCount);

        Picasso.get().load(thumbnail).into(bookIV);



        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyLink.isEmpty()) {

                    Toast.makeText(BookFetchDetails.this, "There is no buy page exist in the Google Books API for this book...", Toast.LENGTH_SHORT).show();
                    return;
                }

                Uri uri = Uri.parse(buyLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
    }
}