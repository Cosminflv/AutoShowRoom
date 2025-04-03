package com.example.footballclubapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlayerStorageHelper {
    private static final String FILE_NAME = "players.json";

    public static void savePlayers(Context context, ArrayList<Player> players) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            new Gson().toJson(players, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Player> loadPlayers(Context context) {
        ArrayList<Player> players = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            InputStreamReader reader = new InputStreamReader(fis);
            Type type = new TypeToken<ArrayList<Player>>(){}.getType();
            players = new Gson().fromJson(reader, type);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }
    public static ArrayList<Player> loadInitialPlayersFromAssets(Context context) {
        ArrayList<Player> players = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("players.json");
            InputStreamReader reader = new InputStreamReader(is);
            Type type = new TypeToken<ArrayList<Player>>(){}.getType();
            players = new Gson().fromJson(reader, type);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public static boolean dataExists(Context context) {
        return context.getFileStreamPath(FILE_NAME).exists();
    }
}
