package com.example.matches.home.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matches.R
import com.example.matches.model.EventDates

class EventDateAdapter : RecyclerView.Adapter<EventDateAdapter.InvoiceViewHolder>(){

    private var eventDatesList = ArrayList<EventDates>()
    private var onClickListener: OnServiceClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.eventdate_card_view, parent, false)
        return InvoiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {
        holder.onBind(eventDatesList[position])

        holder.itemView.findViewById<TextView>(R.id.event_date_card_view).setOnClickListener {

            onClickListener?.onDateClicked(eventDatesList, position)
        }

    }

    override fun getItemCount(): Int {
        return eventDatesList.size
    }


    inner class InvoiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var selectedDate = view.findViewById<TextView>(R.id.event_date_card_view)

        fun onBind(eventDates: EventDates) {
            selectedDate.text = eventDates.date

        }
    }

    fun refreshItems(invoice: List<EventDates>) {
        eventDatesList.clear()
        eventDatesList.addAll(invoice)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: OnServiceClickListener) {
        onClickListener = listener
    }

    interface OnServiceClickListener {

        fun onDateClicked(eventDatesList: ArrayList<EventDates>, position: Int)

    }

}