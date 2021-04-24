package com.example.projetcoachnutrition.Vue;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.User;
import com.example.projetcoachnutrition.R;

import java.util.ArrayList;

public class ActivityImc extends AppCompatActivity {


    private User profil;
    private Controle controle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        controle = Controle.getInstance(this);
    }
}
