package com.example.projetcoachnutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.User;
import com.example.projetcoachnutrition.Vue.ActivityAjoutRepas;
import com.example.projetcoachnutrition.Vue.ActivityFoodDatabase;
import com.example.projetcoachnutrition.Vue.ActivityHistorique;
import com.example.projetcoachnutrition.Vue.ActivityUser;

public class MainActivity extends AppCompatActivity {

    private TextView userName;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controle = controle.getInstance(this);
        setContentView(R.layout.activity_main);




        //si les informations de l'utilisateur ne sont pas défini on lance l'activié user
        if (controle.verifUserExistant() == false) {
            Intent intent = new Intent(this, ActivityUser.class);
            startActivity(intent);
        } else if(controle.verifUserExistant() == true) {

            /*on recupere les informations sur l'user et affichage
             le message d'accueil avec le nom de l'user*/

            User user = controle.loadLastUser();
            userName = (TextView) findViewById(R.id.userName);
            userName.setText("Bonjour" + " " + user.getNom() + " !");
            userName.setGravity(Gravity.CENTER_HORIZONTAL);

        }
    }


    /** Navigation dans l'application**/

    public void goToUserPage(View view) {
        Intent intent = new Intent(this, ActivityUser.class);
        startActivity(intent);
    }

    public void goToHistorique(View view) {
        Intent intent = new Intent(this, ActivityHistorique.class);
        startActivity(intent);
    }


    public void goToDatabase(View view) {
        Intent intent = new Intent(this, ActivityFoodDatabase.class);
        startActivity(intent);
    }

    public void goToAjoutRepas(View view) {
        Intent intent = new Intent(this, ActivityAjoutRepas.class);
        startActivity(intent);
    }
}