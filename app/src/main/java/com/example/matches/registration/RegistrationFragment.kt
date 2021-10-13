package com.example.matches.registration


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.matches.BR
import com.example.matches.R
import com.example.matches.databinding.FragmentRegistrationBinding
import com.example.matches.helper.ApisResponse
import com.example.matches.model.RegisterDetails


class RegistrationFragment : Fragment() {

    lateinit var mViewModel: RegistrationViewModel
    lateinit var mDataBinding: FragmentRegistrationBinding
    private var category: String? = null
    private var selectedEventDate: String? = null
    private val args: RegistrationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_registration,
            container,
            false
        )
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ViewModel execution
        executeViewModel()
        selectedEventDate = args.date.substring(0,10)

        dateImplementation()

        spinnerImplementation()

        mDataBinding.submitBtn.setOnClickListener {

            var registerDetails = RegisterDetails(
                category!!,
                mDataBinding.city.text.toString(),
                mDataBinding.clubId.text.toString().toInt(),
                selectedEventDate!!,
                mDataBinding.mobileNumber.text.toString(),
                mDataBinding.playerName.text.toString()
            )

            register(registerDetails)

        }

    }

    private fun register(registerDetails: RegisterDetails) {
        mViewModel.registerPlayer(registerDetails).observe(viewLifecycleOwner, Observer {apiResponse ->
            when (apiResponse) {
                is ApisResponse.Success -> {

                    Toast.makeText(context, "Register Successfully", Toast.LENGTH_SHORT).show()
                }
                is ApisResponse.Error -> {

                    Toast.makeText(activity, "${apiResponse.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun dateImplementation() {
        mDataBinding.date.text = selectedEventDate
        var date = selectedEventDate!!.substring(0,2)
        var month = selectedEventDate!!.substring(3,5)
        var year = selectedEventDate!!.substring(6,10)

        selectedEventDate = year+"-"+month+"-"+date
        Log.d("TAG", "dateImplementation: $selectedEventDate")

    }

    private fun executeViewModel() {
        mViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        mDataBinding.setVariable(BR.registrationViewModel, mViewModel)
        mDataBinding.lifecycleOwner = this
        mDataBinding.executePendingBindings()
    }

    private fun spinnerImplementation() {
        var spinner = mDataBinding.category
        if (spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.category)
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?, position: Int, id: Long
                ) {
                    category = resources.getStringArray(R.array.category)[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }


}