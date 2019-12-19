package com.ismin.opendataapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar


const val DISPLAY_DETAILS_SCHOOL_KEY = "DISPLAY_DETAILS_SCHOOL_KEY"


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val editName = findViewById<TextView>(R.id.NomEcoletextView)
        val editType = findViewById<TextView>(R.id.TypeTextView)
        val editAdresse = findViewById<TextView>(R.id.AdressetextView)
        val editVille = findViewById<TextView>(R.id.VilletextView)
        val editRegion = findViewById<TextView>(R.id.RegiontextView)
        val editSite = findViewById<TextView>(R.id.SitetextView)
        val editimg = findViewById<ImageView>(R.id.imageSchoolView)
        val school: School = intent.getSerializableExtra(DISPLAY_DETAILS_SCHOOL_KEY) as School

        editName.text = school.name
        editType.text = school.typeEtablissement
        editAdresse.text = school.adresse
        editVille.text = school.ville
        editRegion.text = school.region
        editSite.text = school.SiteInternet

        //Flèche retour sur l'activité
        val actionBar = supportActionBar
        actionBar!!.title = school.name
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Changement d'image selon le type d'établissement
        if (school.typeEtablissement == "École Ingénieur" || school.typeEtablissement == "École habilitée à délivrer un diplôme d'ingénieur" || school.typeEtablissement == "Institut national polytechnique") {
            editimg.setImageResource(R.drawable.ic_chip)
        }
        else if (school.typeEtablissement == "Université") {
            editimg.setImageResource(R.drawable.ic_university)
        }
        else if (school.typeEtablissement == "École de formation artistique") {
            editimg.setImageResource(R.drawable.ic_palette)
        }
        else if (school.typeEtablissement == "École de commerce et de management") {
            editimg.setImageResource(R.drawable.ic_hand)
        }

        else if (school.typeEtablissement == "Établissement supérieur d'architecture") {
            editimg.setImageResource(R.drawable.ic_architecture_draw_of_a_house_on_a_paper)
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
