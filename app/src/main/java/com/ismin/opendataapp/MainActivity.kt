package com.ismin.opendataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener {

    private val SERVER_BASE_URL = "https://data.opendatasoft.com/api/records/1.0/"

    private val NBROWS = 330

    private val recordArray: ArrayList<Record> = arrayListOf()
    private val schoolList: ArrayList<School> = arrayListOf()

    /*arrayListOf(School("Mines St-Etienne Cycle ISMIN", "Gardanne", "École Ingénieur", "https://www.mines-stetienne.fr/formation/ismin/",
        "PACA", "879 route de Mimet",43.445038, 5.479467), School("emlyon business school", "Ecully",
        "Université", "https://www.em-lyon.com/fr",
        "Rhône-Alpes", "23 Avenue Guy de Collongue",45.786430, 4.764251))*/

    private lateinit var mainViewPager: ViewPager
    val fragmentList = ListFragment()
    val fragmentMap = MapFragment()
    val fragmentInfo = InfoFragment()

    lateinit var schoolService: SchoolService


    override fun onMap() {

    }

    override fun onList() {

    }

    override fun onInfo() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SERVER_BASE_URL)
            .build()

        schoolService = retrofit.create(SchoolService::class.java)

        getRecordsFromServer()

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
                getRecordsFromServer()
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getRecordsFromServer(){
        schoolService.getRecords(NBROWS)
            .enqueue(object : Callback<Server> {
                override fun onResponse(
                    call: Call<Server>,
                    response: Response<Server>
                ) {
                    println("Response")
                    val serverData = response.body()
                    recordArray.clear()
                    if (serverData != null) {
                        recordArray.addAll(serverData.records)
                    }
                    else{
                        println("Error: No serverData\n")
                    }
                    getSchools()

                    /*if (listFragmentRead) {
                        transactionList()
                    }
                    else if (mapFragmentRead){
                        transactionMap()
                    }*/
                    fragmentList.afficherList(schoolList)
                    fragmentMap.getSchool(schoolList)
                }

                override fun onFailure(call: Call<Server>, t: Throwable) {
                    println("Error: Failure Response")
                }
            })
    }

    fun getSchools(){
        schoolList.clear()
        lateinit var school: School
        for (i in recordArray.indices){
            school = School(recordArray[i].fields.name, recordArray[i].fields.ville, recordArray[i].fields.typeEtablissement, recordArray[i].fields.SiteInternet, recordArray[i].fields.region, recordArray[i].fields.adresse,recordArray[i].fields.coordonnees[0], recordArray[i].fields.coordonnees[1])
            schoolList.add(school)
        }
        println(schoolList)
    }


}
