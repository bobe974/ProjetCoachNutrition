package com.example.projetcoachnutrition;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Bdd.CoachSanteDbHelper;
import com.example.projetcoachnutrition.Bdd.Repas;

import java.util.ArrayList;

public class ActivityAjoutRepas extends AppCompatActivity {

    // TEST

    ArrayList<Repas> RepasDisponible;
    ListView lesRepasDisponibles;
    Button ValideRepas;
    boolean buttonIsOn;
    ArrayList<Integer> checkedRepas;
    ArrayList<String> QteRepas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_meal);

        QteRepas = new ArrayList<>();
        QteRepas.add("0.5");
        QteRepas.add("1");
        QteRepas.add("1.5");
        QteRepas.add("2");
        QteRepas.add("3");
    }
}


