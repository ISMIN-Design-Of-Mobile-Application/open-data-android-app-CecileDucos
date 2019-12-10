package com.ismin.opendataapp

import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.ismin.opendataapp.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener {
    private val schoolList: ArrayList<School> =
        arrayListOf(School("Mines St-Etienne Cycle ISMIN", "Gardanne", "Ecole Ingénieur", "https://www.mines-stetienne.fr/formation/ismin/",
            "PACA", "879 route de Mimet",43.445038, 5.479467), School("emlyon business school", "Ecully",
            "Université", "https://www.em-lyon.com/fr",
            "Rhône-Alpes", "23 Avenue Guy de Collongue",45.786430, 4.764251))
    lateinit var recyclerView: RecyclerView
    //private lateinit var schoolService:
    private lateinit var mainViewPager: ViewPager
    val fragmentList = ListFragment()
    val fragmentMap = MapFragment()
    val fragmentInfo = InfoFragment()

    public fun getSchoolList(): ArrayList<School> {
        return schoolList
    }

    override fun onMap() {

    }

    override fun onList() {

    }

    override fun onInfo() {

    }

    /*private fun transactionList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = ListFragment()

        fragment.afficherList(schoolList)

        fragmentTransaction.replace(R.id.Fragment, fragment)
        fragmentTransaction.commit()
    }

    private fun transactionMap() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MapFragment()

        fragment.getSchool(schoolList)

        fragmentTransaction.replace(R.id.Fragment, fragment)
        fragmentTransaction.commit()

    }

    private fun transactionInfo() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = InfoFragment()


        fragmentTransaction.replace(R.id.Fragment, fragment)
        fragmentTransaction.commit()

    }*/

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMap = findViewById<Button>(R.id.mapBtn)
        btnMap.setOnClickListener {
            transactionMap()
        }

        val btnList = findViewById<Button>(R.id.listBtn)
        btnList.setOnClickListener {
            transactionList()
        }

        val btnInfo = findViewById<Button>(R.id.infoBtn)
        btnInfo.setOnClickListener {
            transactionInfo()
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mainViewPager = findViewById(R.id.view_pager)
        setSupportActionBar(toolbar)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        sectionPagerAdapter.addFragment(fragmentList, "Liste")
        sectionPagerAdapter.addFragment(fragmentMap, "Carte")
        sectionPagerAdapter.addFragment(fragmentInfo, "Infos")
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        /*//val fragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        //bundle.putSerializable("ENVOIE_DATA", schoolList)
        bundle.putString("TEST", "HELLO WORLD")

        fragmentList.arguments = bundle*/

        fragmentList.afficherList(schoolList)
        fragmentMap.getSchool(schoolList)

    }

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
