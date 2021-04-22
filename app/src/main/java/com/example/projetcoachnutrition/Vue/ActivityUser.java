package com.example.projetcoachnutrition.Vue;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.R;

public class ActivityUser extends AppCompatActivity {


    private EditText nom;
    private EditText age;
    private EditText poids;
    private EditText minCal;
    private EditText maxCal;
    private RadioButton rdHomme, rdFemme;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //instancie par getInstance un controle pour avoir acces au méthode d'ajout, modification
        this.controle = controle.getInstance(this);

        //on récupere les saisies de l'utilisateur

    }
}
