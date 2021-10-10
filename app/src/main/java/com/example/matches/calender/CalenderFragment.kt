package com.example.matches.calender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.matches.BR
import com.example.matches.R
import com.example.matches.databinding.FragmentCalenderBinding


class CalenderFragment : Fragment() {

    lateinit var mViewModel: CalenderViewModel
    lateinit var mDataBinding: FragmentCalenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_calender,
            container,
            false
        )
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ViewModel execution
        executeViewModel()
        mDataBinding.calendarView

    }

    private fun executeViewModel() {
        mViewModel = ViewModelProvider(this).get(CalenderViewModel::class.java)
        mDataBinding.setVariable(BR.calenderViewModel, mViewModel)
        mDataBinding.lifecycleOwner = this
        mDataBinding.executePendingBindings()
    }
}