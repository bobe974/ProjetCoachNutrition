package com.example.projetcoachnutrition.Vue;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Bdd.MyDBAdaptateur;
import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ActivityFoodDatabase extends AppCompatActivity {

    SeekBar caloriesParPortion;
    TextView afficheCalories;
    EditText nomAliment;
    Aliment unaliment;
    ArrayList<Aliment> alimentDispo; //liste qui va contenir les aliment de la bdd
    ListView afficheAliment; // affiche tout les aliments
    private ArrayList<FoodCustomAdapter.ViewHolder> vue;
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
        loadAllFood(); // Chargement des aliments

    }




    /**
     * Chargement de tout les aliments dans l'affichage
     */
    public void loadAllFood(){
        alimentDispo = new ArrayList<Aliment>(controle.loadAliment());
        //affichage des aliments dans la listeView********

        /**************************test*************************/
        afficheAliment = (ListView)findViewById(R.id.displayFoodList);
        final FoodCustomAdapter adapter = new FoodCustomAdapter(ActivityFoodDatabase.this,android.R.layout.simple_list_item_1, alimentDispo);
        afficheAliment.setAdapter(adapter);

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

    /**
     *met a jour les aliments séléctionnés
     * parcours des vue affichagealiment.xml, si une checkbox est sectionné on recupere les valeurs
     * pour la mise a jour
     * @param view
     */
    public void updateFoodToDataBase(View view){

        /*****TESTT AFFICHE INFO ALIMENT COCHE********/
        for(FoodCustomAdapter.ViewHolder lesVuesCheck : vue) {
            if(lesVuesCheck.foodCheckbox.isChecked()){
                int id = Integer.parseInt(lesVuesCheck.foodId.getText().toString());
                controle.updateFoodToDatabase(id,Integer.parseInt(Integer.toString(lesVuesCheck.selectCalories.getProgress())));
                lesVuesCheck.foodCheckbox.getText().toString();
                //Log.d("TAG", "coché: "+ lesVuesCheck.foodCheckbox.getText().toString()+ Integer.parseInt(Integer.toString(lesVuesCheck.selectCalories.getProgress())) + lesVuesCheck.foodId.getText().toString());
            }


        }
    }

    public void deleteFoodToDatabse(View view){
        /*****TESTT AFFICHE INFO ALIMENT COCHE********/
        for(FoodCustomAdapter.ViewHolder lesVuesCheck : vue) {
            if(lesVuesCheck.foodCheckbox.isChecked()){
                int id = Integer.parseInt(lesVuesCheck.foodId.getText().toString());
                controle.deleteFoodToDatabase(id);
                lesVuesCheck.foodCheckbox.getText().toString();
                //Log.d("TAG", "coché: "+ lesVuesCheck.foodCheckbox.getText().toString()+ Integer.parseInt(Integer.toString(lesVuesCheck.selectCalories.getProgress())) + lesVuesCheck.foodId.getText().toString());
            }


        }

        startActivity(getIntent());
    }



    //CLASSE POUR GERER LES LISTES
// gestion des listItem
    private class FoodCustomAdapter extends ArrayAdapter<Aliment> {

        private ArrayList<Aliment> foodAvailable;

        /**
         *
         * @param context
         * @param i
         * @param aliment
         */
        public FoodCustomAdapter(Context context, int i, ArrayList<Aliment> aliment) {
            super(context, i, aliment);
            this.foodAvailable = new ArrayList<Aliment>();
            this.foodAvailable.addAll(aliment);
            vue = new ArrayList<FoodCustomAdapter.ViewHolder>();
        }

        //classe pour sous affichage dans chaque ListItem
        private class ViewHolder {
            TextView nameFood;
            CheckBox foodCheckbox;
            SeekBar selectCalories;
            TextView foodId;
        }

        /**
         * affiche checkbox, nom... dans la liste et conversion de l'objet Aliment en TextView
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            FoodCustomAdapter.ViewHolder holder = null;
            //recupere la position des aliments a afficher
            Aliment aliment = foodAvailable.get(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.affichage_aliment, parent, false);
            }

            holder = new FoodCustomAdapter.ViewHolder();
            // lien avec les objets de l'activity affichage_aliment.xml
            holder.nameFood = (TextView) convertView.findViewById(R.id.foodName);
            holder.foodCheckbox = (CheckBox) convertView.findViewById(R.id.foodCheckbox);
            holder.selectCalories = (SeekBar) convertView.findViewById(R.id.caloriesSeeker);
            holder.foodId = (TextView) convertView.findViewById(R.id.foodId);

            //affichage
            holder.foodId.setText(""+ aliment.getId()+"");
            holder.nameFood.setText(" (" + aliment.getCalories() + ")");
            holder.foodCheckbox.setText(aliment.getName());
            holder.foodCheckbox.setChecked(false);
            holder.foodCheckbox.setTag(aliment);
            holder.selectCalories = (SeekBar) convertView.findViewById(R.id.caloriesSeeker);
            vue.add(holder);

            //gestion seekbar
            holder.selectCalories.setMax(1000); //limite
            holder.selectCalories.setProgress(aliment.getCalories());
            final ViewHolder finalHolder = holder;
            holder.selectCalories.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    String calories = Integer.toString(seekBar.getProgress());
                    finalHolder.nameFood.setText("(" + calories + ")");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            //recupere id des checkbox coché
            final long idCheckbox = getItemId(position);

            //ecoute des checkbox
            holder.foodCheckbox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;

                    cb.setSelected(cb.isChecked());

                }
            });
            // Return the completed view to render on screen
            return convertView;
        }

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