package com.example.weatherfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;


public class MainActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.citySearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                System.out.println("fgfggneminda");
                Intent intent = new Intent(getApplicationContext(), ResultViewActivity.class);
                intent.putExtra("city", s);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //searchView.setQueryHint("Search your city");
        System.out.println(searchView.getQuery());



    }

    public void onSearchClick(View view) {
        System.out.println("jdfvdfcjhd");
        searchView.setIconified(false);

    }

}