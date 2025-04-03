package com.example.footballclubapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ManagePlayerAdapter extends RecyclerView.Adapter<ManagePlayerAdapter.PlayerViewHolder> {

    public interface OnPlayerEditListener {
        void onEdit(Player player, int position);
    }

    public interface OnPlayerDeleteListener {
        void onDelete(int position);
    }

    private List<Player> playerList;
    private OnPlayerEditListener editListener;
    private OnPlayerDeleteListener deleteListener;

    public ManagePlayerAdapter(List<Player> playerList, OnPlayerEditListener editListener, OnPlayerDeleteListener deleteListener) {
        this.playerList = playerList;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.textName.setText(player.name);
        holder.textDetails.setText(player.position + " - " + player.currentTeam);

        holder.buttonEdit.setOnClickListener(v -> editListener.onEdit(player, position));
        holder.buttonDelete.setOnClickListener(v -> deleteListener.onDelete(position));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDetails;
        Button buttonEdit, buttonDelete;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textPlayerName);
            textDetails = itemView.findViewById(R.id.textPlayerDetails);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
