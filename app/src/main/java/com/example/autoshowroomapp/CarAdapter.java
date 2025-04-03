package com.example.autoshowroomapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> carList;
    private Context context;

    public CarAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.carList = cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);

        holder.nameText.setText(car.modelName);
        // Using brandName as the secondary detail
        holder.detailText.setText(car.brandName);
        holder.numberText.setText(String.valueOf(car.number));

        Glide.with(context)
                .load(car.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imageView);

        // Click for details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarDetailActivity.class);
            intent.putExtra("car", car);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameText, detailText, numberText;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCar);
            nameText = itemView.findViewById(R.id.textCarName);
            detailText = itemView.findViewById(R.id.textCarDetail);
            numberText = itemView.findViewById(R.id.textCarNumber);
        }
    }
}
