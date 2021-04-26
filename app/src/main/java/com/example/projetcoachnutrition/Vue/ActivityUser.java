package com.example.projetcoachnutrition.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.User;
import com.example.projetcoachnutrition.R;

import java.util.ArrayList;

public class ActivityUser extends AppCompatActivity {


    private EditText nomUser;
    private EditText ageUser;
    private EditText poidsUser;
    private EditText tailleUser;
    private RadioButton rdHomme, rdFemme;
    private Controle controle;
    private int sexe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //instancie par getInstance un controle pour avoir acces au méthode d'ajout, modification
        this.controle = controle.getInstance(this);

        //on récupere les saisies de l'utilisateur
        nomUser = findViewById(R.id.userName);
        ageUser = findViewById(R.id.userAge);
        tailleUser = findViewById(R.id.userTaille);
        poidsUser = findViewById(R.id.userPoids);
        rdFemme = findViewById(R.id.radioButtonFemme);
        rdHomme = findViewById(R.id.radioButtonHomme);


        if (controle.verifUserExistant() == true) {
            getLastUser();
        }


    }


    /**
     *  Récupere dernier données de l'utilisateur enregistré
     * @return
     */
    public void getLastUser(){
        User lastuser = controle.loadLastUser();
        nomUser.setText(lastuser.getNom());
        ageUser.setText(String.valueOf(lastuser.getAge()));
        tailleUser.setText(String.valueOf(lastuser.getTaille()));
        poidsUser.setText(String.valueOf((int) lastuser.getPoids()));
        int sexeLastUser = lastuser.getSexe();
        if(sexeLastUser == 1){
            rdFemme.setChecked(false);
            rdHomme.setChecked(true);
        }else{
            rdFemme.setChecked(true);
            rdHomme.setChecked(false);
        }

    }

    /**
     *  Check si radioButton Checked Homme/Femme
     *
     */
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButtonFemme:
                if (checked)
                    rdHomme.setChecked(false);
                    rdFemme.setChecked(true);
                    break;
            case R.id.radioButtonHomme:
                if (checked)
                    rdFemme.setChecked(false);
                    rdHomme.setChecked(true);
                    break;
        }
    }

    /**
     *  le genre de l'user 1 pour homme 0 femme
     * @return
     */

    public void setGenre(){
        if(rdHomme.isChecked()){
            this.sexe = 1;
        }else{
            if(rdFemme.isChecked()){
                this.sexe = 0;
                }
        }
    }

    /**
     * ajoute l'utilisateur dans la base ou modifie les données
     * @param view
     */
    public void confirmUser(View view){
        String nom = nomUser.getText().toString();
        int age = Integer.parseInt(ageUser.getText().toString());
        float poids = Float.parseFloat(poidsUser.getText().toString());
        int taille= Integer.parseInt(tailleUser.getText().toString());
        setGenre(); //attribut 1 ou 0 a sexe

        this.controle.creerUser(nom,age,poids,taille,this.sexe);
        Toast.makeText(getApplicationContext(),   " effectué !", Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    /**
     * redirige vers la page de suivi
     * @param view
     */
    public void goToImg(View view){
        Intent intent = new Intent(this, ActivityImc.class);
        startActivity(intent);
    }


}
