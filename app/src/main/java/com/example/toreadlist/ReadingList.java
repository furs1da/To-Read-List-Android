package com.example.toreadlist;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.os.Bundle;

public class ReadingList extends AppCompatActivity {

    private ArrayList<BookItem> bookInfoArrayList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

        getSupportActionBar().setTitle("Your Reading List");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0582ca"));
        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        progressBar = findViewById(R.id.bookItemLoadingBarPB);

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
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                },500);
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
                        Intent i=new Intent(getApplicationContext(),ReadingList.class);
                        startActivity(i);
                    }
                },500);
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
                        Intent i=new Intent(getApplicationContext(),AboutPage.class);
                        startActivity(i);
                    }
                },500);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void getBooksInfo() {

        // creating a new array list.

        DBHelper dbhelp=new DBHelper(this);
        bookInfoArrayList = new ArrayList<>();
        ArrayList<BookItem> bookInfoArrayListAdapter = new ArrayList<>();

        bookInfoArrayList = dbhelp.readBookList();

        progressBar.setVisibility(View.GONE);

                // inside on response method we are extracting all our json data.
                try {
                    for (int i = 0; i < bookInfoArrayList.size(); i++) {

                        bookInfoArrayListAdapter.add(bookInfoArrayList.get(i));

                        BookItemAdapter adapter = new BookItemAdapter(bookInfoArrayListAdapter, ReadingList.this);


                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReadingList.this, RecyclerView.VERTICAL, false);
                        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.bookItemRecyclerView);


                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        mRecyclerView.setAdapter(adapter);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ReadingList.this, "No Data Found :(" + e, Toast.LENGTH_SHORT).show();
                }
        }

}