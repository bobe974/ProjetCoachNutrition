package com.example.projetcoachnutrition.Vue;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.R;

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
                this.sexe = 0;}
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



}
