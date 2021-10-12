package com.example.matches.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.matches.BR
import com.example.matches.R
import com.example.matches.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    lateinit var mViewModel: HomeViewModel
    lateinit var mDataBinding: FragmentHomeBinding
    private var category: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
        )
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ViewModel execution
        executeViewModel()

        editTextListenerImplementation()

        spinnerImplementation()

        mDataBinding.submitBtn.setOnClickListener {
            Log.d("TAG", "homeFragment onViewCreated:${mDataBinding.clubId.text} ")
            Log.d("TAG", "homeFragment onViewCreated:${mDataBinding.playerName.text} ")
            Log.d("TAG", "homeFragment onViewCreated:${mDataBinding.mobileNumber.text} ")
            Log.d("TAG", "homeFragment onViewCreated:${mDataBinding.date.text} ")
            Log.d("TAG", "homeFragment onViewCreated:${category} ")
            Log.d("TAG", "homeFragment onViewCreated:${mDataBinding.city.text} ")
        }
    }

    private fun editTextListenerImplementation() {
        var date = mDataBinding.date
        date.addTextChangedListener(object : TextWatcher {

            private var current = ""
            private var ddmmyyyy = "DDMMYYYY"
            private var cal: Calendar = Calendar.getInstance()

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() != current) {
                    var clean = s.toString().replace("[^\\d.]|\\.".toRegex(), "")
                    val cleanC = current.replace("[^\\d.]|\\.".toRegex(), "")
                    val cl = clean.length
                    var sel = cl
                    var i = 2
                    while (i <= cl && i < 6) {
                        sel++
                        i += 2
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean == cleanC) sel--
                    if (clean.length < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length)
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        var day = clean.substring(0, 2).toInt()
                        var mon = clean.substring(2, 4).toInt()
                        var year = clean.substring(4, 8).toInt()
                        mon = if (mon < 1) 1 else if (mon > 12) 12 else mon
                        cal[Calendar.MONTH] = mon - 1
                        year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                        cal[Calendar.YEAR] = year
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012
                        day = if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(Calendar.DATE) else day
                        clean = String.format("%02d%02d%02d", day, mon, year)
                    }
                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8))
                    sel = if (sel < 0) 0 else sel
                    current = clean
                    date.setText(current)
                    date.setSelection(if (sel < current.length) sel else current.length)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun executeViewModel() {
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mDataBinding.setVariable(BR.homeViewModel, mViewModel)
        mDataBinding.lifecycleOwner = this
        mDataBinding.executePendingBindings()
    }

    private fun spinnerImplementation() {
        var spinner = mDataBinding.category
        if (spinner != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.category))
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    category = resources.getStringArray(R.array.category)[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

}