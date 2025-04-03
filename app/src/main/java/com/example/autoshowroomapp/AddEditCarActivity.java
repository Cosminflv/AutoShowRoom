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

public class AddEditCarActivity extends AppCompatActivity {

    private EditText editModelName, editBrandName, editNumber, editImageUrl, editWikiUrl;
    private EditText editFuelType, editAge, editMileage, editColor;
    private Car existingCar = null;

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
        setContentView(R.layout.activity_add_edit_car);

        editModelName = findViewById(R.id.editModelName);
        editBrandName = findViewById(R.id.editBrandName);
        editNumber = findViewById(R.id.editNumber);
        editImageUrl = findViewById(R.id.editImageUrl);
        editWikiUrl = findViewById(R.id.editWikiUrl);
        editFuelType = findViewById(R.id.editFuelType);
        editAge = findViewById(R.id.editAge);
        editMileage = findViewById(R.id.editMileage);
        editColor = findViewById(R.id.editColor);

        imageSelected = findViewById(R.id.imageSelected);
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        Intent intent = getIntent();
        if (intent.hasExtra("car")) {
            existingCar = (Car) intent.getSerializableExtra("car");

            editModelName.setText(existingCar.modelName);
            editBrandName.setText(existingCar.brandName);
            editNumber.setText(String.valueOf(existingCar.number));
            editImageUrl.setText(existingCar.imageUrl);
            editWikiUrl.setText(existingCar.wikipediaUrl);
            editFuelType.setText(existingCar.fuelType);
            editAge.setText(String.valueOf(existingCar.age));
            editMileage.setText(String.valueOf(existingCar.mileage));
            editColor.setText(existingCar.color);

            if (!existingCar.imageUrl.isEmpty()) {
                selectedImageUri = existingCar.imageUrl;
                Glide.with(this).load(existingCar.imageUrl).into(imageSelected);
            }
        }

        buttonSave.setOnClickListener(v -> {
            String modelName = editModelName.getText().toString();
            String brandName = editBrandName.getText().toString();
            String numberStr = editNumber.getText().toString();
            if (numberStr.trim().isEmpty()) {
                editNumber.setError("Please enter a number!");
                return;
            }
            int number = Integer.parseInt(numberStr);

            String imageUrl = selectedImageUri.isEmpty() ? editImageUrl.getText().toString() : selectedImageUri;
            String wikiUrl = editWikiUrl.getText().toString();
            String fuelType = editFuelType.getText().toString();
            int age = Integer.parseInt(editAge.getText().toString());
            int mileage = Integer.parseInt(editMileage.getText().toString());
            String color = editColor.getText().toString();

            // Here id is set to 0. Adjust as needed (e.g., auto-generated or based on existingCar.id)
            Car newCar = new Car(0, modelName, brandName, number, imageUrl, wikiUrl, fuelType, age, mileage, color);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("car", newCar);
            // If editing, you might pass along the original position/index (if needed)
            if (existingCar != null) {
                int position = getIntent().getIntExtra("position", -1);
                resultIntent.putExtra("position", position);
            }
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
