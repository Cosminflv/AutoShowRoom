package com.example.footballclubapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManagePlayersActivity extends AppCompatActivity {
    private static final int ADD_REQUEST = 1;
    private static final int EDIT_REQUEST = 2;

    private ArrayList<Player> players = new ArrayList<>();
    private ManagePlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_players);

        // TODO: Înlocuiește cu încărcare din XML dacă vrei
        players = PlayerStorageHelper.loadPlayers(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewManage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManagePlayerAdapter(players, this::onEditPlayer, this::onDeletePlayer);
        recyclerView.setAdapter(adapter);

        Button buttonAdd = findViewById(R.id.buttonAddPlayer);
        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditPlayerActivity.class);
            startActivityForResult(intent, ADD_REQUEST);
        });
    }

    private void onEditPlayer(Player player, int position) {
        Intent intent = new Intent(this, AddEditPlayerActivity.class);
        intent.putExtra("player", player);
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_REQUEST);
    }

    private void onDeletePlayer(int position) {
        players.remove(position);
        PlayerStorageHelper.savePlayers(this, players);
        adapter.notifyItemRemoved(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Player player = (Player) data.getSerializableExtra("player");
            int position = data.getIntExtra("position", -1);

            if (requestCode == ADD_REQUEST) {
                players.add(player);
                PlayerStorageHelper.savePlayers(this, players);
                adapter.notifyItemInserted(players.size() - 1);
            } else if (requestCode == EDIT_REQUEST && position != -1) {
                players.set(position, player);
                PlayerStorageHelper.savePlayers(this, players);
                adapter.notifyItemChanged(position);
            }
        }
    }
}
