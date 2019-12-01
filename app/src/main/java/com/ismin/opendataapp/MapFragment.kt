package com.ismin.opendataapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ismin.opendataapp.R
import com.ismin.opendataapp.School


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var listener: OnFragmentInteractionListener? = null
    private var schoolList: ArrayList<School> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onStart() {
        super.onStart()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onMap()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (i in 0 until schoolList.size) {

            var school = schoolList[i]
            val coordonnees = LatLng(school.latitude, school.longitude)
            mMap.addMarker(MarkerOptions().position(coordonnees).title(school.name))


        }
        val gardanne = LatLng(43.445038, 5.479467)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gardanne))
    }


    fun getSchool(listSchool: ArrayList<School>) {
        this.schoolList.clear()
        this.schoolList.addAll(listSchool)

    }
}