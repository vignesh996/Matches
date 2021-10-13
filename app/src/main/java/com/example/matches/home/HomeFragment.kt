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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matches.BR
import com.example.matches.R
import com.example.matches.databinding.FragmentHomeBinding
import com.example.matches.home.adapter.EventDateAdapter
import com.example.matches.model.EventDates
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), EventDateAdapter.OnServiceClickListener {

    lateinit var mViewModel: HomeViewModel
    lateinit var mDataBinding: FragmentHomeBinding
    var adapter = EventDateAdapter()
    private var eventDateList=getDateList()

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

        // Adapting recyclerView
        executeRecyclerView()


    }

    private fun executeRecyclerView() {
        var recyclerView = mDataBinding.dateRecyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.setLayoutManager(linearLayoutManager)
        recyclerView.setAdapter(adapter)
        adapter.setOnClickListener(this)
        adapter.refreshItems(eventDateList)
    }


    private fun executeViewModel() {
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mDataBinding.setVariable(BR.homeViewModel, mViewModel)
        mDataBinding.lifecycleOwner = this
        mDataBinding.executePendingBindings()
    }

    private fun getDateList(): ArrayList<EventDates> {
        var eventDateList = ArrayList<EventDates>()

        eventDateList.add(EventDates("20-10-2021 to 21-10-2021"))
        eventDateList.add(EventDates("26-10-2021 to 27-10-2021"))
        eventDateList.add(EventDates("30-10-2021 to 31-10-2021"))
        eventDateList.add(EventDates("03-11-2021 to 04-11-2021"))
        eventDateList.add(EventDates("08-11-2021 to 09-11-2021"))
        eventDateList.add(EventDates("14-11-2021 to 15-11-2021"))
        eventDateList.add(EventDates("20-11-2021 to 21-11-2021"))

        return eventDateList
    }

    override fun onDateClicked(eventDatesList: ArrayList<EventDates>, position: Int) {

        var date = eventDatesList[position].date
        Log.d("TAG", "onDateClicked: ${date}")

        val action = HomeFragmentDirections.actionHomeFragmentToRegistrationFragment(date)
        findNavController().navigate(action)
    }

}