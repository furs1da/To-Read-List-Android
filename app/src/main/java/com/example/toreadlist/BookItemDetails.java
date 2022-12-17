package com.example.toreadlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
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

public class BookItemDetails extends AppCompatActivity {

    String title, publisher, publishedDate, description, thumbnail, infoLink, language;
    Boolean isCompleted;
    int pageCount, id;

    TextView titleTV, publisherTV, descTV, pageTV, publishDateTV, languageTV, isCompletedTV;
    Button bookDetailsFullInformationBtn, bookDetailsEditBtn, bookDetailsRemoveBtn;
    private ImageView bookIV;

    private Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item_details);
        getSupportActionBar().setTitle("Book Details");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0582ca"));
        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        titleTV = findViewById(R.id.bookItemDetailsTitleTV);
        publisherTV = findViewById(R.id.bookItemDetailsPublisherTV);
        descTV = findViewById(R.id.bookItemDetailsDescriptionTV);
        pageTV = findViewById(R.id.bookItemDetailsNoPagesTV);
        isCompletedTV = findViewById(R.id.bookItemDetailsCompletedTV);

        publishDateTV = findViewById(R.id.bookItemDetailsPublishDateTV);
        languageTV = findViewById(R.id.bookItemDetailsLanguageTV);
        bookDetailsFullInformationBtn = findViewById(R.id.ItemDetailsInfoDetails);


        bookDetailsEditBtn = findViewById(R.id.bookItemDetailsEditBtn);
        bookDetailsRemoveBtn = findViewById(R.id.bookItemDetailsRemoveBtn);

        bookIV = findViewById(R.id.bookItemDetailsIV);


        title = getIntent().getStringExtra("title");
        publisher = getIntent().getStringExtra("publisher");
        publishedDate = getIntent().getStringExtra("publishedDate");
        description = getIntent().getStringExtra("description");
        pageCount = getIntent().getIntExtra("pageCount", 0);
        id = getIntent().getIntExtra("id", 0);

        thumbnail = getIntent().getStringExtra("imageUrl");
        infoLink = getIntent().getStringExtra("bookDetailsLink");
        language = getIntent().getStringExtra("language");

        isCompleted = getIntent().getBooleanExtra("isCompleted", false);


        titleTV.setText(title);
        publisherTV.setText(publisher);
        publishDateTV.setText("Published On : " + publishedDate);
        descTV.setText(description);
        languageTV.setText("Language" + language);
        isCompletedTV.setText(isCompleted == true ? "Completed" : "In Progress");

        pageTV.setText("# Of Pages : " + pageCount);

        if(thumbnail != null && thumbnail != "")
            Picasso.get().load(thumbnail).into(bookIV);

        DBHelper dbhelp = new DBHelper( this);

        this.appContext = getApplicationContext();


        bookDetailsFullInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoLink.isEmpty()) {

                    Toast.makeText(BookItemDetails.this, "There is no buy page exist in the Google Books API for this book...", Toast.LENGTH_SHORT).show();
                    return;
                }

                Uri uri = Uri.parse(infoLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

        bookDetailsEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(appContext, EditBookItem.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("title", title);

                try{
                    i.putExtra("language", language_codes.get(language));
                }
                catch(Exception ex)
                {
                    i.putExtra("language", language);
                }


                i.putExtra("publisher", publisher);
                i.putExtra("publishedDate", publishedDate);
                i.putExtra("description", description);
                i.putExtra("pageCount",pageCount);
                i.putExtra("imageUrl", thumbnail);
                i.putExtra("isCompleted", isCompleted);
                i.putExtra("id", id);
                i.putExtra("bookDetailsLink", infoLink);

                appContext.startActivity(i);
            }
        });



        bookDetailsRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    dbhelp.deleteBook(id);
                    Toast.makeText(BookItemDetails.this, "This book was removed from your reading list...", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),ReadingList.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception ex)
                {

                }

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
            case R.id.FindBook:
                Toast.makeText(this, "Find new book", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(
                //getApplicationContext(), MainActivity.class));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                }, 500);
                return true;
            case R.id.ReadingList:
                Toast.makeText(this, "Library",
                        Toast.LENGTH_SHORT).show();
                //                To start an activity:
                //startActivity(new Intent(
                //getApplicationContext(), ReadingList.class));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), ReadingList.class);
                        startActivity(i);
                    }
                }, 500);
                return true;
            case R.id.About:
                Toast.makeText(this, "About",
                        Toast.LENGTH_SHORT).show();
                //                To start an activity:
                //startActivity(new Intent(
                //getApplicationContext(), AboutPage.class));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), AboutPage.class);
                        startActivity(i);
                    }
                }, 500);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}