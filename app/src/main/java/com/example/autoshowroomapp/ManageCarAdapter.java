package com.example.autoshowroomapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ManageCarAdapter extends RecyclerView.Adapter<ManageCarAdapter.CarViewHolder> {

    public interface OnCarEditListener {
        void onEdit(Car car, int position);
    }

    public interface OnCarDeleteListener {
        void onDelete(int position);
    }

    private List<Car> carList;
    private OnCarEditListener editListener;
    private OnCarDeleteListener deleteListener;

    public ManageCarAdapter(List<Car> carList, OnCarEditListener editListener, OnCarDeleteListener deleteListener) {
        this.carList = carList;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.textName.setText(car.modelName);
        // For details we show brand name; you can add more info if needed
        holder.textDetails.setText(car.brandName);

        holder.buttonEdit.setOnClickListener(v -> editListener.onEdit(car, position));
        holder.buttonDelete.setOnClickListener(v -> deleteListener.onDelete(position));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDetails;
        Button buttonEdit, buttonDelete;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textCarName);
            textDetails = itemView.findViewById(R.id.textCarDetails);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
