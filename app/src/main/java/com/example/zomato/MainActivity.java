package com.example.zomato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zomato.Adapters.MainAdapter;
import com.example.zomato.Models.MainModel;
import com.example.zomato.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        // public MainModel(int image, int price, String name, String description) -> parameters

        list.add(new MainModel(R.drawable.food1 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food2 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food3 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food4 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food5 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food6 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food8 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food9 , "50" , "Samosa" , "Taste you will never forget"));
        list.add(new MainModel(R.drawable.food10 , "50" , "Samosa" , "Taste you will never forget"));

        MainAdapter adapter = new MainAdapter(list , this );
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.menuOrders:
                startActivity(new Intent(MainActivity.this , OrderActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}