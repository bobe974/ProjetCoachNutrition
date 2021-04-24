package com.example.projetcoachnutrition.Vue;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
    private ImageView afficheImage;
    private float img;
    private int sexe;
    private String message;
    private List<User> lesProfils;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        controle = Controle.getInstance(this);
        afficheImage = (ImageView)findViewById(R.id.afficheImage);
        afficheImg = (TextView) findViewById(R.id.afficheImg);
        recupInfoProfil();
    }


    public void recupInfoProfil(){
        lesProfils = new ArrayList<User>(controle.loadUser());

        for(User unuser : lesProfils){
            
            Log.d("TAG", "RECUPINFOPROFIL LISTE: "+unuser.getNom());
            this.img = unuser.getImg();
            this.message = unuser.getMessage();
            //affichage des résultats
            afficheResult();

        }

    }


    /**
     * affiche l'imc de l'user et l'image qui correspond
     */
    private void afficheResult(){

        //affiche la bonne image
        if (message == "normal"){
            afficheImage.setImageResource(R.drawable.normal);

        }else{
            if(message == "trop élevé"){
                afficheImage.setImageResource(R.drawable.eleve);
                //lblIMG.setTextColor(Color.RED);
            }else{
               // afficheImage.setImageResource(R.drawable.faible);

            }
        }
        //affiche message
        afficheImg.setText(String.format("%.01f", img )+ ": IMG " + message);  //String.format  2 chiffre apres la virgule


    }

}
