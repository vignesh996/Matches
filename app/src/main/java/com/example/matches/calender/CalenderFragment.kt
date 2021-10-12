package com.example.matches.calender

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.matches.BR
import com.example.matches.R
import com.example.matches.databinding.FragmentCalenderBinding
import java.util.*


class CalenderFragment : Fragment() {

    lateinit var mViewModel: CalenderViewModel
    lateinit var mDataBinding: FragmentCalenderBinding
    lateinit var calenderView: CalendarView


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

         calenderView = mDataBinding.calendarView

        calenderViewMethod()




    }


    private fun calenderViewMethod() {
        var events = ArrayList<EventDay>()

        var calendar1 = Calendar.getInstance()
        Log.d("TAG", "onViewCreated: date ${calendar1.get(Calendar.DATE)}")
        Log.d("TAG", "onViewCreated: month ${calendar1.get(Calendar.MONTH)}")
        Log.d("TAG", "onViewCreated: ${Calendar.DAY_OF_MONTH}")
        calendar1.add(Calendar.DAY_OF_MONTH, 10)

        events.add(EventDay(calendar1, R.drawable.ic_badminton_svgrepo_com, Color.parseColor("#EA1A0B")))

        calenderView.setEvents(events)

        calenderView.setOnDayClickListener{
            Log.d("TAG", "onViewCreated: date clicked ${it.calendar.get(Calendar.DATE)}")
        }

        calenderView.setOnPreviousPageChangeListener {
            Log.d("TAG", "onViewCreated: previous clicked")

        }

        calenderView.setOnForwardPageChangeListener {
            Log.d("TAG", "onViewCreated: forword clicked")

        }
    }

    private fun executeViewModel() {
        mViewModel = ViewModelProvider(this).get(CalenderViewModel::class.java)
        mDataBinding.setVariable(BR.calenderViewModel, mViewModel)
        mDataBinding.lifecycleOwner = this
        mDataBinding.executePendingBindings()
    }

}