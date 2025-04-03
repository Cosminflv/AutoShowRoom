package com.example.autoshowroomapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AddEditPlayerActivity extends AppCompatActivity {

    private EditText editName, editPosition, editNumber, editNationality, editTeam, editImageUrl, editWikiUrl;
    private EditText editFullName, editAge, editGames, editGoals, editFoot, editShooting, editPassing, editDribbling, editPhysicality;

    private int editingPosition = -1;
    private Player existingPlayer = null;

    private static final int PICK_IMAGE_REQUEST = 1;
    private String selectedImageUri = "";
    private ImageView imageSelected;

    private String copyImageToInternalStorage(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            File file = new File(getFilesDir(), "img_" + System.currentTimeMillis() + ".jpg");
            OutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();

            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_player);

        editName = findViewById(R.id.editName);
        editPosition = findViewById(R.id.editPosition);
        editNumber = findViewById(R.id.editNumber);
        editNationality = findViewById(R.id.editNationality);
        editTeam = findViewById(R.id.editTeam);
        editImageUrl = findViewById(R.id.editImageUrl);
        editWikiUrl = findViewById(R.id.editWikiUrl);

        editFullName = findViewById(R.id.editFullName);
        editAge = findViewById(R.id.editAge);
        editGames = findViewById(R.id.editGames);
        editGoals = findViewById(R.id.editGoals);
        editFoot = findViewById(R.id.editFoot);
        editShooting = findViewById(R.id.editShooting);
        editPassing = findViewById(R.id.editPassing);
        editDribbling = findViewById(R.id.editDribbling);
        editPhysicality = findViewById(R.id.editPhysicality);

        imageSelected = findViewById(R.id.imageSelected);
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        Intent intent = getIntent();
        if (intent.hasExtra("player")) {
            existingPlayer = (Player) intent.getSerializableExtra("player");
            editingPosition = intent.getIntExtra("position", -1);

            editName.setText(existingPlayer.name);
            editPosition.setText(existingPlayer.position);
            editNumber.setText(String.valueOf(existingPlayer.number));
            editNationality.setText(existingPlayer.nationality);
            editTeam.setText(existingPlayer.currentTeam);
            editImageUrl.setText(existingPlayer.imageUrl);
            editWikiUrl.setText(existingPlayer.wikipediaUrl);

            editFullName.setText(existingPlayer.fullName);
            editAge.setText(String.valueOf(existingPlayer.age));
            editGames.setText(String.valueOf(existingPlayer.games));
            editGoals.setText(String.valueOf(existingPlayer.goals));
            editFoot.setText(existingPlayer.preferredFoot);
            editShooting.setText(String.valueOf(existingPlayer.shooting));
            editPassing.setText(String.valueOf(existingPlayer.passing));
            editDribbling.setText(String.valueOf(existingPlayer.dribbling));
            editPhysicality.setText(String.valueOf(existingPlayer.physicality));

            if (!existingPlayer.imageUrl.isEmpty()) {
                selectedImageUri = existingPlayer.imageUrl;
                Glide.with(this).load(existingPlayer.imageUrl).into(imageSelected);
            }
        }

        buttonSave.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String position = editPosition.getText().toString();

            String numberStr = editNumber.getText().toString();
            if (numberStr.trim().isEmpty()) {
                editNumber.setError("Introduceți un număr!");
                return;
            }

            int number = Integer.parseInt(numberStr);
            String nationality = editNationality.getText().toString();
            String team = editTeam.getText().toString();
            String imageUrl = selectedImageUri.isEmpty() ? editImageUrl.getText().toString() : selectedImageUri;
            String wikiUrl = editWikiUrl.getText().toString();

            String fullName = editFullName.getText().toString();
            int age = Integer.parseInt(editAge.getText().toString());
            int games = Integer.parseInt(editGames.getText().toString());
            int goals = Integer.parseInt(editGoals.getText().toString());
            String foot = editFoot.getText().toString();
            int shooting = Integer.parseInt(editShooting.getText().toString());
            int passing = Integer.parseInt(editPassing.getText().toString());
            int dribbling = Integer.parseInt(editDribbling.getText().toString());
            int physicality = Integer.parseInt(editPhysicality.getText().toString());

            Player newPlayer = new Player(0, name, position, number, nationality, team, imageUrl, wikiUrl,
                    foot, age, shooting, dribbling, passing, physicality, games, goals, fullName);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("player", newPlayer);
            resultIntent.putExtra("position", editingPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            selectedImageUri = copyImageToInternalStorage(uri);
            Glide.with(this).load(selectedImageUri).into(imageSelected);
        }
    }
}
