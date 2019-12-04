package com.ismin.opendataapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    lateinit var recyclerView: RecyclerView
    private var schoolList : ArrayList<School> = ArrayList()
    private val adapter = SchoolAdapter(schoolList, ::detailsElement)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = rootView.findViewById(R.id.schoolList)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        fun onList()
    }

    fun afficherList(listSchool: ArrayList<School>) {
        this.schoolList.clear()
        this.schoolList.addAll(listSchool)
        adapter.notifyDataSetChanged()
    }

    fun detailsElement(position: Int) {
        //Toast.makeText(context, "merci jcccc V2", Toast.LENGTH_LONG).show()
        var school = schoolList[position]
        val intent =  Intent(context, DetailsActivity::class.java)
        intent.putExtra("DISPLAY_DETAILS_SCHOOL_KEY", school)
        this.startActivity(intent)
    }




}
