package com.example.autoshowroomapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PlayerDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView fullNameText, positionText, nationalityText, ageText, gamesText, goalsText, teamText;
    private Button buttonMoreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        imageView = findViewById(R.id.imagePlayerDetail);
        fullNameText = findViewById(R.id.textFullNameDetail);
        positionText = findViewById(R.id.textPositionDetail);
        nationalityText = findViewById(R.id.textNationalityDetail);
        ageText = findViewById(R.id.textAgeDetail);
        gamesText = findViewById(R.id.textGamesDetail);
        goalsText = findViewById(R.id.textGoalsDetail);
        teamText = findViewById(R.id.textTeamDetail);


        buttonMoreDetails = findViewById(R.id.buttonMoreDetails);

        Player player = (Player) getIntent().getSerializableExtra("player");

        if (player != null) {
            TextView fullNameLabel = findViewById(R.id.textFullNameLabel);
            fullNameText.setText(player.name);
            positionText.setText(player.position);
            ageText.setText(String.valueOf(player.age));
            gamesText.setText(String.valueOf(player.games));
            goalsText.setText(String.valueOf(player.goals));
            teamText.setText(player.currentTeam);
            nationalityText.setText(player.nationality);
            fullNameLabel.setText(player.fullName);

            Glide.with(this).load(player.imageUrl).into(imageView);

            buttonMoreDetails.setOnClickListener(v -> {
                Intent intent = new Intent(this, PlayerMoreDetailsActivity.class);
                intent.putExtra("player", player);
                startActivity(intent);
            });
        }
    }
}
