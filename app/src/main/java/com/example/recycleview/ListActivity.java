package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recycleview.adapter.StarAdapter;
import com.example.recycleview.beans.Star;
import com.example.recycleview.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity"; // Define the TAG variable here

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, stars);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Assuming you have imported LinearLayoutManager
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("stars");
    }

    public void init() {
        stars.add(new Star("kate bosworth",  "https://source.unsplash.com/500x500/?kate+bosworth", 3.5f));
        stars.add(new Star("george clooney", "https://source.unsplash.com/500x500/?george+clooney", 3));
        stars.add(new Star("michelle rodriguez", "https://source.unsplash.com/500x500/?michelle+rodriguez", 5));
        stars.add(new Star("george clooney", "https://source.unsplash.com/500x500/?george+clooney", 1));
        stars.add(new Star("louise bouroin", "https://source.unsplash.com/500x500/?louise+bouroin", 5));
        stars.add(new Star("louise bouroin", "https://source.unsplash.com/500x500/?louise+bouroin", 1));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

}
