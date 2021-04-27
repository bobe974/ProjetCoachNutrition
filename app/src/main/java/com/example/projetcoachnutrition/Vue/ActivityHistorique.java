package com.example.projetcoachnutrition.Vue;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetcoachnutrition.Controler.Controle;
import com.example.projetcoachnutrition.Modele.Aliment;
import com.example.projetcoachnutrition.Modele.Repas;
import com.example.projetcoachnutrition.R;

import java.util.ArrayList;

public class ActivityHistorique extends AppCompatActivity {

    private Controle controle;
    private ArrayList<Repas> lesRepas;
    private ArrayList<ActivityHistorique.FoodCustomAdapter.ViewHolder> vue;
    private ListView laliste;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        this.controle = Controle.getInstance(this);
        laliste = findViewById(R.id.afficheHistorique);
        loadAllMeal();
    }

    public void loadAllMeal() {
        lesRepas = new ArrayList<Repas>(controle.loadMeal());
        //affichage des aliments dans la listeView********

        /**************************test*************************/
        final ActivityHistorique.FoodCustomAdapter adapter = new ActivityHistorique.FoodCustomAdapter(ActivityHistorique.this, android.R.layout.simple_list_item_1, lesRepas);
        laliste.setAdapter(adapter);

    }


    //CLASSE POUR GERER LES LISTES
// gestion des listItem
    private class FoodCustomAdapter extends ArrayAdapter<Repas> {

        private ArrayList<Repas> repasdispo;

        /**
         *
         * @param context
         * @param i
         * @param repas
         */
        private FoodCustomAdapter(Context context, int i, ArrayList<Repas> repas) {
            super(context, i, repas);
            this.repasdispo = new ArrayList<Repas>();
            this.repasdispo.addAll(repas);
            vue = new ArrayList<ActivityHistorique.FoodCustomAdapter.ViewHolder>();
        }

        //classe pour sous affichage dans chaque ListItem
        private class ViewHolder {
            TextView repasDate;
            TextView totalCalories;
            Button btnSup;
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
            ActivityHistorique.FoodCustomAdapter.ViewHolder holder = null;
            //recupere la position desrepas a afficher
            Repas repas = repasdispo.get(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.affichage_in_historique, parent, false);
            }

            holder = new ActivityHistorique.FoodCustomAdapter.ViewHolder();
            // lien avec les objets de l'activity affichage_aliment.xml
            holder.repasDate = (TextView) convertView.findViewById(R.id.dateRepas);
            holder.totalCalories = (TextView) convertView.findViewById(R.id.totalCalories);
            holder.btnSup = (Button) convertView.findViewById(R.id.Btnsup);
            convertView.setTag(holder);

            //affichage
            holder.repasDate.setText("" + repas.getDate());
            holder.totalCalories.setText(repas.getCalories() +"");
            vue.add(holder);

            // Return the completed view to render on screen
            return convertView;
        }

    }
}
