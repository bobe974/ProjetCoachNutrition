package com.example.projetcoachnutrition.Vue;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.User;
import com.example.projetcoachnutrition.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityImc extends AppCompatActivity {


    private User profil;
    private Controle controle;
    private TextView afficheImg;
    private ListView historiqueImg;
    private float img;
    private List<User> lesProfils;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        controle = Controle.getInstance(this);
        afficheImg = findViewById(R.id.afficheImg);
        recupInfoProfil();
    }


    public void recupInfoProfil(){
        lesProfils = new ArrayList<User>(controle.loadUser());
        float img = 0;
        for(User unuser : lesProfils){
            
            Log.d("TAG", "RECUPINFOPROFIL LISTE: "+unuser.getNom());
            img = unuser.getImg();
        }
        afficheImg.setText("Mon IMG actuel: "+String.format("%.01f", img ));  //String.format  2 chiffre apres la virgule

    }
}
