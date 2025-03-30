package com.example.autoshowroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autoshowroom.entities.CarBrand;

import java.util.List;

public class CarBrandAdapter extends RecyclerView.Adapter<CarBrandAdapter.CarBrandViewHolder> {

    public interface BrandClickListener{
        void onCarBrandClick(int position);
    }

    private Context context;
    private List<CarBrand> brands;

    private BrandClickListener listener;

    public CarBrandAdapter(Context context, List<CarBrand> brands, BrandClickListener listener)
    {
        this.context = context;
        this.brands = brands;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarBrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.brand_item, parent, false);
        return new CarBrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarBrandViewHolder holder, int position) {
        CarBrand brand = brands.get(position);
        holder.brandName.setText(brand.getName());
        int imgId = context.getResources().getIdentifier(brand.getImagePath(), "drawable", context.getPackageName());
        int hll = R.drawable.toyota;
        holder.brandImage.setImageResource(imgId);
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public class CarBrandViewHolder extends RecyclerView.ViewHolder{
        ImageView brandImage;
        TextView brandName;

        public CarBrandViewHolder(@NonNull View itemView){
            super(itemView);
            brandImage = itemView.findViewById(R.id.brandImage);
            brandName = itemView.findViewById(R.id.brandName);

            itemView.setOnClickListener(v -> listener.onCarBrandClick(getAdapterPosition()));
        }
    }
}
