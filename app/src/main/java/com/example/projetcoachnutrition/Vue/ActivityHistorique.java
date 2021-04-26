package com.example.projetcoachnutrition.Vue;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.Repas;
import com.example.projetcoachnutrition.R;

import java.util.ArrayList;

public class ActivityHistorique extends AppCompatActivity {

    private Controle controle;
    private ArrayList<Repas> lesRepas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi_repas);

        this.controle = Controle.getInstance(this);
    }

    public void loadAllMeal() {
        lesRepas = new ArrayList<Repas>(controle.loadMeal());
        //affichage des aliments dans la listeView********

        /**************************test*************************/
        //final ActivityAjoutRepas.FoodCustomAdapter2 adapter = new ActivityAjoutRepas.FoodCustomAdapter2(ActivityAjoutRepas.this, android.R.layout.simple_list_item_1, alimentsDispo);
        //lesRepasDisponibles.setAdapter(adapter);

    }
}
