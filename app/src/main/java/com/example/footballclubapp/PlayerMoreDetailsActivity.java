package com.example.footballclubapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerMoreDetailsActivity extends AppCompatActivity {

    private TextView textName, textTeam, textMorePosition, textMoreNumber, textMoreNationality,
            textMoreFoot, textMoreAge, textMoreRating;
    private ProgressBar progressSkills, progressMental, progressPhysical, progressShooting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_more_details);

        // Inițializări text
        textName = findViewById(R.id.textMoreName);
        textTeam = findViewById(R.id.textMoreTeam);
        textMorePosition = findViewById(R.id.textMorePosition);
        textMoreNumber = findViewById(R.id.textMoreNumber);
        textMoreNationality = findViewById(R.id.textMoreNationality);
        textMoreFoot = findViewById(R.id.textMoreFoot);
        textMoreAge = findViewById(R.id.textMoreAge);
        textMoreRating = findViewById(R.id.textMoreRating);

        // Inițializări progress bar-uri
        progressSkills = findViewById(R.id.progressSkills);
        progressMental = findViewById(R.id.progressMental);
        progressPhysical = findViewById(R.id.progressPhysical);
        progressShooting = findViewById(R.id.progressShooting);

        // Buton Wikipedia
        Button buttonWiki = findViewById(R.id.buttonOpenWiki);

        // Primim jucătorul
        Player player = (Player) getIntent().getSerializableExtra("player");

        if (player != null) {
            textName.setText(player.name);
            textTeam.setText(player.currentTeam);
            textMorePosition.setText(player.position);
            textMoreNumber.setText(String.valueOf(player.number));
            textMoreNationality.setText(player.nationality);
            textMoreFoot.setText(player.preferredFoot);
            textMoreAge.setText(String.valueOf(player.age));
            textMoreRating.setText("Overall Rating: " + player.shooting);

            progressSkills.setProgress(player.dribbling);
            progressMental.setProgress(player.passing);
            progressPhysical.setProgress(player.physicality);
            progressShooting.setProgress(player.shooting);
        }

        // Deschide Wikipedia
        buttonWiki.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerMoreDetailsActivity.this, PlayerWebActivity.class);
            intent.putExtra("wikipediaUrl", player.wikipediaUrl);
            startActivity(intent);
        });
    }
}
