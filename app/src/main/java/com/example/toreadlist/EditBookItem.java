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
import android.widget.CompoundButton;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.toreadlist.BookValues.*;
import static java.lang.Integer.parseInt;

public class EditBookItem extends AppCompatActivity implements View.OnClickListener {

    String title, publisher, publishedDate, description, thumbnail, infoLink, language;
    Boolean isCompleted;
    int pageCount, id;

    EditText titleET, publisherET, descET, pageET, languageET, imageUrlET, infoLinkET, publishedDateET;
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

        publishedDateET = findViewById(R.id.editBookPublishedDateET);



        languageET = findViewById(R.id.editBookLanguageET);

        cancelEditBtn = findViewById(R.id.cancelEditBtn);
        confirmEditBtn = findViewById(R.id.confirmEditBtn);





        title = getIntent().getStringExtra("title");
        titleET.setText(title);

        publisher = getIntent().getStringExtra("publisher");
        publisherET.setText(publisher);


        publishedDate = getIntent().getStringExtra("publishedDate");
        publishedDateET.setText(publishedDate);

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


        yesRB.setOnClickListener(this);
        noRB.setOnClickListener(this);
        confirmEditBtn.setOnClickListener(this);
        cancelEditBtn.setOnClickListener(this);
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
    private boolean validateForm() {
        Boolean error = true;

        if (publisherET.length() == 0) {
            publisherET.setError("Publisher field is required.");
            error = false;
        }

        if (publishedDateET.length() == 0) {
            publishedDateET.setError("Published date field is required.");
            error = false;
        }

        if (languageET.length() == 0) {
            languageET.setError("Language field is required.");
            error = false;
        }
        if (titleET.length() == 0) {
            titleET.setError("Title field is required.");
            error = false;
        }
        if (descET.length() == 0) {
            descET.setError("Description field is required.");
            error = false;
        }
        if (imageUrlET.length() == 0) {
            imageUrlET.setError("Image URL field is required.");
            error = false;
        }
        if (infoLinkET.length() == 0) {
            infoLinkET.setError("Info Link field is required.");
            error = false;
        }

        try {
            Picasso.get().load(imageUrlET.getText().toString());
        }
        catch (Exception ex)
        {
            imageUrlET.setError("Image URL field must be valid.");
            error = false;
        }

        if(pageET.getText().toString() == "")
            pageET.setText("0");
        if(parseInt(pageET.getText().toString()) < 1)
        {
            pageET.setError("A book must have at least 1 page.");
            error = false;
        }
        // after all validation return true.
        return error;
    }

    @Override
    public void onClick(View view) {
        //radiobutton and button
        switch (view.getId()) {
            case R.id.yesEditRB:
                isCompleted = true;
                break;

            case R.id.noEditRB:
                isCompleted = false;
                break;

            case R.id.confirmEditBtn:
                if(validateForm()) {
                    DBHelper dbhelp = new DBHelper(this);

                    BookItem bookItem = new BookItem(titleET.getText().toString(),
                            descET.getText().toString(),
                            publisherET.getText().toString(),
                            publishedDateET.getText().toString(),
                            parseInt(pageET.getText().toString()),
                            imageUrlET.getText().toString(),
                            infoLinkET.getText().toString(),
                            languageET.getText().toString(),
                            yesRB.isChecked(),
                            id
                    );
                    dbhelp.updateBook(bookItem);

                    Intent i=new Intent(getApplicationContext(),ReadingList.class);
                    startActivity(i);
                    finish();
                    //database code to transfer data to table -- next class
                    Toast.makeText(this,"Data Submitted Successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this,"Validation errors.",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.cancelEditBtn:
                Intent i=new Intent(getApplicationContext(),ReadingList.class);
                startActivity(i);
                finish();
                break;
        }
    }
}