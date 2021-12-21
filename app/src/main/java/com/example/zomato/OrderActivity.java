package com.example.zomato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.zomato.Adapters.OrderAdapter;
import com.example.zomato.Models.OrderModel;
import com.example.zomato.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);

        ArrayList <OrderModel> list = helper.getOrders();

        //OrderModel(int orderImage, String soldItemName, String price, String orderNumber) -> Parameters



        OrderAdapter adapter = new OrderAdapter(list , this );
        binding.orderRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerview.setLayoutManager(layoutManager);


    }
}