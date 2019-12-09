package com.ismin.opendataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener {

    private val SERVER_BASE_URL = "https://data.opendatasoft.com/api/records/1.0/"

    private val NBROWS = 330

    private val schoolList: ArrayList<School> = arrayListOf()
    private val recordArray: ArrayList<Record> = arrayListOf()

    /*arrayListOf(School("Mines St-Etienne Cycle ISMIN", "Gardanne", "Ecole Ingénieur", "https://www.mines-stetienne.fr/formation/ismin/",
        "PACA", "879 route de Mimet",43.445038, 5.479467), School("emlyon business school", "Ecully",
        "Université", "https://www.em-lyon.com/fr",
        "Rhône-Alpes", "23 Avenue Guy de Collongue",45.786430, 4.764251))*/

    private var listFragmentRead: Boolean = false
    private var mapFragmentRead: Boolean = false
    private var infoFragmentRead: Boolean = false

    lateinit var recyclerView: RecyclerView

    lateinit var schoolService: SchoolService


    override fun onMap() {

    }

    override fun onList() {

    }

    override fun onInfo() {

    }

    private fun transactionList() {
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

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SERVER_BASE_URL)
            .build()

        schoolService = retrofit.create(SchoolService::class.java)

        getRecordsFromServer()

        val btnMap = findViewById<Button>(R.id.mapBtn)
        btnMap.setOnClickListener {
            mapFragmentRead = true
            listFragmentRead = false
            infoFragmentRead = false
            transactionMap()
        }

        val btnList = findViewById<Button>(R.id.listBtn)
        btnList.setOnClickListener {
            mapFragmentRead = false
            listFragmentRead = true
            infoFragmentRead = false
            transactionList()
        }

        val btnInfo = findViewById<Button>(R.id.infoBtn)
        btnInfo.setOnClickListener {
            mapFragmentRead = false
            listFragmentRead = false
            infoFragmentRead = true
            transactionInfo()
        }
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

                    if (listFragmentRead) {
                        transactionList()
                    }
                    else if (mapFragmentRead){
                        transactionMap()
                    }

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
