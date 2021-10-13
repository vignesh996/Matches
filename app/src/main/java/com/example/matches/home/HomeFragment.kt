package com.example.matches.home

import android.app.DatePickerDialog
import android.os.Bundle
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
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    lateinit var mViewModel: HomeViewModel
    lateinit var mDataBinding: FragmentHomeBinding
    private var category: String? = null
    var myCalender = Calendar.getInstance()


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

        dateImplementation()
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

    private fun dateImplementation() {
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR,year)
            myCalender.set(Calendar.MONTH,month)
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalender)
        }

        mDataBinding.date.setOnClickListener {
            DatePickerDialog(requireContext(), datePicker,myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),
                    myCalender.get(Calendar.DAY_OF_MONTH)).show()

        }

    }

    private fun updateLable(myCalender: Calendar) {
        var myFormat = "dd-MM-yyyy"
        var sdf = SimpleDateFormat(myFormat,Locale.UK)
        mDataBinding.date.setText(sdf.format(myCalender.time))
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
                                            view: View?, position: Int, id: Long) {
                    category = resources.getStringArray(R.array.category)[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

}