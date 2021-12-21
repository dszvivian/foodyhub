package com.example.zomato;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zomato.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type", 0) == 1) {

            int image = getIntent().getIntExtra("foodimage", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("mainName");
            String description = getIntent().getStringExtra("description");

            binding.detailimage.setImageResource(image);
            binding.detailPrice.setText(String.format("%d", price));
            binding.detailFoodname.setText(name);
            binding.detailDescription.setText(description);


            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //insertOrder(String name , String phone , int price , int image , String desc , String foodname , int quantity) -> parameters
                    boolean isInserted = helper.insertOrder(binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            price,
                            image,
                            description,
                            name,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );

                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else{

            // String name 1, String phone 2, int price 3, int image 4, String desc 5, String foodname 6, int quantity 7 -> index of parameters
            int id = getIntent().getIntExtra("id" , 0);
            Cursor cursor = helper.getorderbyId(id);
            binding.detailimage.setImageResource(cursor.getInt(4));
            binding.detailPrice.setText(String.format("%d", cursor.getInt(3)));
            binding.detailFoodname.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));

            binding.namebox.setText(cursor.getString(1));
            binding.phonebox.setText(cursor.getString(2));


            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isupdated = helper.orderUpdate(binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            Integer.parseInt(binding.detailPrice.getText().toString()),
                            cursor.getInt(4) ,
                            binding.detailDescription.getText().toString(),
                            binding.detailFoodname.getText().toString(),
                            1,
                            id);

                    if(isupdated)
                        Toast.makeText(DetailActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error while Updating", Toast.LENGTH_SHORT).show();

                }

            });

        }

    }
}