package com.example.projetcoachnutrition.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.R;

public class MainActivity extends AppCompatActivity {

    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //si les informations de l'utilisateur ne sont pas défini on lance l'activié user

          /**  if (!CoachSanteContentProvider.isUserAlreadyDefined()) {
            Intent intent = new Intent(this, ActivityUser.class);
            startActivity(intent);
        } else {**/

            /*on recupere les informations sur l'user et affichage
             le message d'accueil avec le nom de l'user*/

           /** User user = CoachSanteContentProvider.getCurrentUser();
            userName = (TextView) findViewById(R.id.userName);
            userName.setText("Bonjour" + " " + user.getNom() + " !");
            userName.setGravity(Gravity.CENTER_HORIZONTAL);

        }**/
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