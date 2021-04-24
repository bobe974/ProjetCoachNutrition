package com.example.projetcoachnutrition.Vue;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.Repas;
import com.example.projetcoachnutrition.R;
import java.util.ArrayList;

public class ActivityAjoutRepas extends AppCompatActivity {


    private ListView lesRepasDisponibles;
    private Button BtnValideRepas;
    private Button BtnSupRepas;
    private ArrayList<String> QteRepas;
    private ArrayList<Repas> RepasDisponible;
    private ArrayList<Aliment> alimentsDispo;
    private ArrayList<ActivityAjoutRepas.FoodCustomAdapter2.ViewHolder2> vue;
    private Controle controle;




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_meal);

        controle = Controle.getInstance(this);

        BtnValideRepas = findViewById(R.id.valideRepas);
        BtnSupRepas = findViewById(R.id.supRepas);
        lesRepasDisponibles = findViewById(R.id.listeRepas);
        QteRepas = new ArrayList<>();
        QteRepas.add("0.5");
        QteRepas.add("1");
        QteRepas.add("1.5");
        QteRepas.add("2");
        QteRepas.add("3");
        loadAllFood();
    }

    /**
     * Chargement de tout les aliments dans l'affichage
     */
    public void loadAllFood(){
        alimentsDispo = new ArrayList<Aliment>(controle.loadAliment());
        //affichage des aliments dans la listeView********

        /**************************test*************************/
        final ActivityAjoutRepas.FoodCustomAdapter2 adapter = new ActivityAjoutRepas.FoodCustomAdapter2(ActivityAjoutRepas.this,android.R.layout.simple_list_item_1, alimentsDispo);
        lesRepasDisponibles.setAdapter(adapter);

    }

    //CLASSE POUR GERER LES LISTES
// gestion des listItem
    public class FoodCustomAdapter2 extends ArrayAdapter<Aliment> {

        private ArrayList<Aliment> foodAvailable;

        /**
         *
         * @param context
         * @param i
         * @param aliment
         */
        private FoodCustomAdapter2(Context context, int i, ArrayList<Aliment> aliment) {
            super(context, i, aliment);
            this.foodAvailable = new ArrayList<Aliment>();
            this.foodAvailable.addAll(aliment);
            vue = new ArrayList<ActivityAjoutRepas.FoodCustomAdapter2.ViewHolder2>();
        }

        //classe pour sous affichage dans chaque ListItem
        private class ViewHolder2 {
            TextView nameFood;
            CheckBox foodCheckbox;
            TextView foodId;
            Spinner portions;
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
            ActivityAjoutRepas.FoodCustomAdapter2.ViewHolder2 holder = null;
            //recupere la position des aliments a afficher
            Aliment aliment = foodAvailable.get(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.affichage_qte_aliment, parent, false);
            }


            holder = new FoodCustomAdapter2.ViewHolder2();
            // lien avec les objets de l'activity affichage_qte_aliment.xml
            holder.nameFood = (TextView) convertView.findViewById(R.id.foodName);
            holder.foodCheckbox = (CheckBox) convertView.findViewById(R.id.foodCheckbox);
            holder.foodId = (TextView) convertView.findViewById(R.id.foodId);
            holder.portions = (Spinner) convertView.findViewById(R.id.spinnerPortion);

            //affichage
            holder.foodId.setText(""+ aliment.getId()+"");
            holder.nameFood.setText(" (" + aliment.getCalories() + ")");
            holder.foodCheckbox.setText(aliment.getName());
            holder.foodCheckbox.setChecked(false);
            holder.foodCheckbox.setTag(aliment);
            vue.add(holder);


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
}


