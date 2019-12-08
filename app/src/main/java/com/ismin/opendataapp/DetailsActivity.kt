package com.ismin.opendataapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


const val DISPLAY_DETAILS_SCHOOL_KEY = "DISPLAY_DETAILS_SCHOOL_KEY"


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        //kikou
        val editName = findViewById<TextView>(R.id.NomEcoletextView)
        val editType = findViewById<TextView>(R.id.TypeTextView)
        val editAdresse = findViewById<TextView>(R.id.AdressetextView)
        val editVille = findViewById<TextView>(R.id.VilletextView)
        val editRegion = findViewById<TextView>(R.id.RegiontextView)
        val editSite = findViewById<TextView>(R.id.SitetextView)
        val editimg = findViewById<ImageView>(R.id.imageSchoolView)
        val school: School = intent.getSerializableExtra(DISPLAY_DETAILS_SCHOOL_KEY) as School
        //Toast.makeText(this, school.name, Toast.LENGTH_LONG).show()
        editName.text = school.name
        editType.text = school.typeEtablissement
        editAdresse.text = school.adresse
        editVille.text = school.ville
        editRegion.text = school.region
        editSite.text = school.SiteInternet
        val actionBar = supportActionBar
        actionBar!!.title = school.name
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        if (school.typeEtablissement == "Ecole Ingénieur" || school.typeEtablissement == "Ecole habilitée à délivrer un diplôme d'ingénieur" || school.typeEtablissement == "Institut national polytechnique") {
            editimg.setImageResource(R.drawable.ic_chip)
        }
        else if (school.typeEtablissement == "Université") {
            editimg.setImageResource(R.drawable.ic_university)
        }
        else if (school.typeEtablissement == "Ecole de formation artistique") {
            editimg.setImageResource(R.drawable.ic_palette)
        }
        else if (school.typeEtablissement == "Ecole de commerce et de management") {
            editimg.setImageResource(R.drawable.ic_hand)
        }

        else if (school.typeEtablissement == "Etablissement supérieur d'architecture") {
            editimg.setImageResource(R.drawable.ic_architecture_draw_of_a_house_on_a_paper)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
