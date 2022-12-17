package com.example.toreadlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    String title, publisher, publishedDate, description, thumbnail, previewLink, infoLink, buyLink, language, averageRating;
    int pageCount;
    private ArrayList<String> authors;

    TextView titleTV, publisherTV, descTV, pageTV, publishDateTV, languageTV, averageRatingTV;
    Button bookDetailsFullInformationBtn;
    private ImageView bookIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_fetch_details);
        getSupportActionBar().setTitle("Book Details");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0582ca"));
        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        titleTV = findViewById(R.id.bookDetailsTitleTV);
        publisherTV = findViewById(R.id.bookDetailsPublisherTV);
        descTV = findViewById(R.id.bookDetailsDescriptionTV);
        pageTV = findViewById(R.id.bookDetailsNoPagesTV);
        publishDateTV = findViewById(R.id.bookDetailsPublishDateTV);
        languageTV = findViewById(R.id.bookDetailsLanguageTV);
        averageRatingTV = findViewById(R.id.bookDetailsAverageRatingTV);
        bookDetailsFullInformationBtn = findViewById(R.id.bookDetailsFullInformationBtn);
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
        averageRating = getIntent().getStringExtra("averageRating");


        titleTV.setText(title);
        publisherTV.setText(publisher);
        publishDateTV.setText("Published On : " + publishedDate);
        descTV.setText(description);
        languageTV.setText(language);
        averageRatingTV.setText(averageRating);

        pageTV.setText("# Of Pages : " + pageCount);

        Picasso.get().load(thumbnail).into(bookIV);



        bookDetailsFullInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoLink.isEmpty()) {

                    Toast.makeText(BookFetchDetails.this, "There is no buy page exist in the Google Books API for this book...", Toast.LENGTH_SHORT).show();
                    return;
                }

                Uri uri = Uri.parse(infoLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Find new book",
                        Toast.LENGTH_SHORT).show();
//                To start an activity:
//                startActivity(new Intent(
//                        getApplicationContext(), MainActivity.class));
                return true;
            case R.id.item2:
                Toast.makeText(this, "Library",
                        Toast.LENGTH_SHORT).show();
                //                To start an activity:
//                startActivity(new Intent(
//                        getApplicationContext(), MainActivity.class));
                return true;
            case R.id.item3:
                Toast.makeText(this, "About",
                        Toast.LENGTH_SHORT).show();
                //                To start an activity:
//                startActivity(new Intent(
//                        getApplicationContext(), MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}