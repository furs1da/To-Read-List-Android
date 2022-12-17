package com.example.toreadlist;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static com.example.toreadlist.BookValues.*;

public class EditBookItem extends AppCompatActivity {

    String title, publisher, publishedDate, description, thumbnail, infoLink, language;
    Boolean isCompleted;
    int pageCount, id;

    EditText titleET, publisherET, descET, pageET, languageET, imageUrlET, infoLinkET;
    DatePicker publishedDatePicker;
    RadioButton yesRB, noRB;
    Button cancelEditBtn, confirmEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_item);

        getSupportActionBar().setTitle("Edit Book");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0582ca"));
        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        titleET = findViewById(R.id.editBookTitleET);
        publisherET = findViewById(R.id.editBookPublisherET);
        descET = findViewById(R.id.editBookDescriptionET);
        pageET = findViewById(R.id.editBookPagesNumberET);
        infoLinkET = findViewById(R.id.editBookInformationLinkET);
        imageUrlET = findViewById(R.id.editBookImageUrlET);
        yesRB = findViewById(R.id.yesEditRB);
        noRB = findViewById(R.id.noEditRB);

        publishedDatePicker = findViewById(R.id.dlgDateTimePickerDate);



        languageET = findViewById(R.id.editBookLanguageET);

        cancelEditBtn = findViewById(R.id.cancelEditBtn);
        confirmEditBtn = findViewById(R.id.confirmEditBtn);





        title = getIntent().getStringExtra("title");
        titleET.setText(title);

        publisher = getIntent().getStringExtra("publisher");
        publisherET.setText(publisher);


        publishedDate = getIntent().getStringExtra("publishedDate");


        description = getIntent().getStringExtra("description");
        descET.setText(description);

        pageCount = getIntent().getIntExtra("pageCount", 0);
        pageET.setText(String.valueOf(pageCount));


        id = getIntent().getIntExtra("id", 0);

        thumbnail = getIntent().getStringExtra("imageUrl");
        imageUrlET.setText(thumbnail);

        infoLink = getIntent().getStringExtra("bookDetailsLink");
        infoLinkET.setText(infoLink);

        language = getIntent().getStringExtra("language");
        languageET.setText(language);

        isCompleted = getIntent().getBooleanExtra("isCompleted", false);

        yesRB.setChecked(isCompleted);
        noRB.setChecked(!isCompleted);
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