package com.example.zomato.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.DBHelper;
import com.example.zomato.DetailActivity;
import com.example.zomato.Models.OrderModel;
import com.example.zomato.OrderActivity;
import com.example.zomato.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder> {

    ArrayList<OrderModel> list ;
    Context context;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample , parent , false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final OrderModel model = list.get(position);

        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.price.setText(model.getPrice());
        holder.orderNumber.setText(model.getOrderNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , DetailActivity.class);

                intent.putExtra("id" , Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type1" , 2);

                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                DBHelper helper = new DBHelper(context);

                AlertDialog delete = new  AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure to delete")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

//                                helper.deleteOrder(model.getOrderNumber());
                                if(helper.deleteOrder(model.getOrderNumber()) > 0)
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                                else
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();;
                            }
                        })
                        .show();


                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView orderImage ;
        TextView soldItemName , price , orderNumber ;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.orderimage);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            price = itemView.findViewById(R.id.orderPrice);
            orderNumber = itemView.findViewById(R.id.orderNumber);

        }
    }

}
