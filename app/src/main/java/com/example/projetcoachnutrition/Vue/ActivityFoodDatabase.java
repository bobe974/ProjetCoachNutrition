package com.example.projetcoachnutrition.Vue;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.R;

public class ActivityFoodDatabase extends AppCompatActivity {

    SeekBar caloriesParPortion;
    TextView afficheCalories;
    EditText nomAliment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_database);

        //on récupere les saisies de l'utilisateur
        nomAliment = (EditText) findViewById(R.id.nomAliment);
        caloriesParPortion = (SeekBar) findViewById(R.id.caloriesPerPortion);
        caloriesParPortion.setMax(1000);  // limite max de la seekbarre

        //affichage des calories
        afficheCalories = (TextView) findViewById(R.id.afficheCalories);
        String calories = "Calories : " + Integer.toString(caloriesParPortion.getProgress());
        afficheCalories.setText(calories);

        //gestion seekbar
        caloriesParPortion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                String calories = Integer.toString(seekBar.getProgress());
                afficheCalories.setText("Calories : " + calories);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
