package com.example.projetcoachnutrition.Vue;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Bdd.MyDBAdaptateur;
import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.R;

public class ActivityFoodDatabase extends AppCompatActivity {

    SeekBar caloriesParPortion;
    TextView afficheCalories;
    EditText nomAliment;
    MyDBAdaptateur unAdapater;
    Aliment unaliment;
    private Controle controle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controle = controle.getInstance(this);
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


    /**ajout de l'aliment saisie dans la base
     *
     * @param view
     */
    public void addFoodToDatabase(View view) {

        //créer un aliment
        /*
        Aliment unaliment = new Aliment(0,nomAliment.getText().toString(),caloriesParPortion.getProgress());
        //ouverture de la base
        MyDBAdaptateur bdd = new MyDBAdaptateur(this);
        bdd.open();
        bdd.insertAliment(unaliment);
        bdd.close();
        Toast.makeText(getApplicationContext(), "nous avons ajouter"+ unaliment.getName(), Toast.LENGTH_SHORT).show();
        */
        String aliment = nomAliment.getText().toString();
         this.controle.creerAliment(nomAliment.getText().toString(),caloriesParPortion.getProgress());
        Toast.makeText(getApplicationContext(),   " on a ajouté " + aliment + " !", Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }




    /*
    public void addFoodToDatabase(View view) {
        //Verifier qu'elle n'existe pas deja
        String foodToAdd = nomAliment.getText().toString();

        //test
        if(foodToAdd.equals("")) {

            Toast.makeText(getApplicationContext(),"il faut un nom gros noob", Toast.LENGTH_LONG).show();
        } else {
                if(CoachSanteContentProvider.checkIfFoodAlreadyExists(foodToAdd)) {
                Toast.makeText(getApplicationContext(), "l'aliment est deja dans la base...", Toast.LENGTH_LONG).show();
            } else {
                //ajout dans la bdd
                ContentValues toInsert = new ContentValues();
                toInsert.put(CoachSanteDbHelper.getFoodColumn(), foodToAdd);
                toInsert.put(CoachSanteDbHelper.getEstimatedCaloriesForAPortion(), Integer.parseInt(Integer.toString(caloriesParPortion.getProgress())));
                Toast.makeText(getApplicationContext(), "on a ajouté" + " " + foodToAdd + " !", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());
            }
        }



    /**
     * met a jour les alimments (ListItems) avec checkbox coché
     * @param view*/
     /*
    public void updateFoodToDatabase(View view) {

        for(FoodCustomAdapter.ViewHolder temp : views) {
            CoachSanteContentProvider.updateFoodToDatabase(temp.foodCheckbox.getText().toString(), Integer.parseInt(Integer.toString(temp.selectCalories.getProgress())));

        }

    } */

    /**
     * supprime les ListItems avec checkbox coché
     * @param view
     */ /*
    public void deleteFoodFromDatabase(View view) {

        for(FoodCustomAdapter.ViewHolder temp: views) {
            if(temp.foodCheckbox.isChecked()) {
                CoachSanteContentProvider.deleteFood(temp.foodCheckbox.getText().toString());
            }
        }

        finish();
        startActivity(getIntent());

    } */




}