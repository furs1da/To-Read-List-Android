package com.example.toreadlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutPage extends AppCompatActivity {

    Button notifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_page);
        getSupportActionBar().setTitle("About Page");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0582ca"));
        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        notifyBtn = findViewById(R.id.notify_btn);

        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AboutPage.this, "This is project #10 notification");
                builder.setContentTitle("The Title");
                builder.setContentText("Hello from group 10");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AboutPage.this);
                managerCompat.notify(1, builder.build());
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
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                },500);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}