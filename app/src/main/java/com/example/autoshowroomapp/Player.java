package com.example.autoshowroomapp;

import java.io.Serializable;

public class Player implements Serializable {
    public int id;
    public String name;
    public String position;
    public int number;
    public String nationality;
    public String currentTeam;
    public String imageUrl;
    public String wikipediaUrl;

    // 🔥 Adăugat pentru detalii extinse
    public String preferredFoot;
    public int age;
    public int shooting;
    public int dribbling;
    public int passing;
    public int physicality;

    public int games;

    public int goals;
    public String fullName;

    public Player() {
    }

    public Player(int id, String name, String position, int number, String nationality,
                  String currentTeam, String imageUrl, String wikipediaUrl) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.nationality = nationality;
        this.currentTeam = currentTeam;
        this.imageUrl = imageUrl;
        this.wikipediaUrl = wikipediaUrl;
    }

    // ✅ Poți adăuga un constructor complet dacă vrei să folosești toate câmpurile dintr-un loc
    public Player(int id, String name, String position, int number, String nationality,
                  String currentTeam, String imageUrl, String wikipediaUrl,
                  String preferredFoot, int age, int shooting, int dribbling, int passing, int physicality, int games, int goals,
                  String fullName) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.nationality = nationality;
        this.currentTeam = currentTeam;
        this.imageUrl = imageUrl;
        this.wikipediaUrl = wikipediaUrl;
        this.preferredFoot = preferredFoot;
        this.age = age;
        this.shooting = shooting;
        this.dribbling = dribbling;
        this.passing = passing;
        this.physicality = physicality;
        this.games=games;
        this.goals=goals;
        this.fullName=fullName;
    }
}
