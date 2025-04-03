package com.example.autoshowroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshowroom.entities.CarModel;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private final List<CarModel> carList;
    private final Context context;

    public CarAdapter(Context context, List<CarModel> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_card_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarModel car = carList.get(position);
        holder.carName.setText(car.getName());

        // Retrieve the resource id from the imagePath in CarModel
        int resId = context.getResources().getIdentifier(car.getImagePath(), "drawable", context.getPackageName());
        holder.carImage.setImageResource(resId);

        // Set the car description
        holder.carDescription.setText(car.getDescription());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView carName, carDescription;
        ImageView carImage;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.carName);
            carImage = itemView.findViewById(R.id.carImage);
            carDescription = itemView.findViewById(R.id.carDescription);
        }
    }
}
