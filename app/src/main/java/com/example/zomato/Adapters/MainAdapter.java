package com.example.zomato.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.DetailActivity;
import com.example.zomato.Models.MainModel;
import com.example.zomato.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder>{

    ArrayList<MainModel> list ;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_mainfood , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final MainModel model = list.get(position);
        holder.foodimage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("foodimage" , model.getImage());
                intent.putExtra("mainName" , model.getName());
                intent.putExtra("price" , model.getPrice());
                intent.putExtra("description" , model.getDescription());
                intent.putExtra("type" , 1);
                context.startActivity(intent);


            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView foodimage;
        TextView mainName , price  , description ;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            foodimage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);

        }
    }

}
