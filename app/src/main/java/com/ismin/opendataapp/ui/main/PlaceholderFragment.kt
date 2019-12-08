package com.ismin.opendataapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ismin.opendataapp.ListFragment
import com.ismin.opendataapp.MainActivity
import com.ismin.opendataapp.R
import com.ismin.opendataapp.School
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), ListFragment.OnFragmentInteractionListener {
    private val schoolList: ArrayList<School> =
        arrayListOf(
            School("Mines St-Etienne Cycle ISMIN", "bobobobobobobobobobobosignéPierrre", "Ecole Ingénieur", "https://www.mines-stetienne.fr/formation/ismin/",
                "PACA", "879 route de Mimet",43.445038, 5.479467), School("emlyon business school", "Ecully",
                "Université", "https://www.em-lyon.com/fr",
                "Rhône-Alpes", "23 Avenue Guy de Collongue",45.786430, 4.764251)
        )
    override fun onList() {

    }

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root : View
        val nbPage = arguments?.getInt(ARG_SECTION_NUMBER)

        if (nbPage == 1) {
            val fragment = ListFragment()
            fragment.afficherList(schoolList)
            root = inflater.inflate(R.layout.fragment_list, container, false)
            System.out.println("page 1")

        }

        else if (nbPage == 2 ) {
            root = inflater.inflate(R.layout.fragment_map, container, false)
        }

        else {
            root = inflater.inflate(R.layout.fragment_info, container, false)
        }
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}