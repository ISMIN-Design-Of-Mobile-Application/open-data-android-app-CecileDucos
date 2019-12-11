package com.ismin.opendataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener {
    private val schoolList: ArrayList<School> =
        arrayListOf(School("Mines St-Etienne Cycle ISMIN", "Gardanne", "École Ingénieur", "https://www.mines-stetienne.fr/formation/ismin/",
            "PACA", "879 route de Mimet",43.445038, 5.479467), School("emlyon business school", "Ecully",
            "Université", "https://www.em-lyon.com/fr",
            "Rhône-Alpes", "23 Avenue Guy de Collongue",45.786430, 4.764251))

    private lateinit var mainViewPager: ViewPager
    val fragmentList = ListFragment()
    val fragmentMap = MapFragment()
    val fragmentInfo = InfoFragment()



    override fun onMap() {

    }

    override fun onList() {

    }

    override fun onInfo() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mainViewPager = findViewById(R.id.view_pager)
        setSupportActionBar(toolbar) //Création de la barre d'outil contenant le menu
        //Création des 3 onglets
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        sectionPagerAdapter.addFragment(fragmentList, "Liste")
        sectionPagerAdapter.addFragment(fragmentMap, "Carte")
        sectionPagerAdapter.addFragment(fragmentInfo, "Infos")
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        //Initialisation des valeurs des fragments avec le tableau actuel
        fragmentList.afficherList(schoolList)
        fragmentMap.getSchool(schoolList)

    }

    //Création du menu avec l'option refresh
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.empty_list -> {
                //TODO : Doit faire le refresh des données
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }


}
