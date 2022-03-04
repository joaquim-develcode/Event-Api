package com.example.events.ui.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.events.R
import com.example.events.model.Event
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private var events: List<Event> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(event.date)
        holder.date.text = String.format("Date: %s", dateString)
        holder.title.text = event.title
        holder.description.text = event.description
        holder.price.text = event.price.toString()
        holder.latitude.text = event.latitude.toString()
        holder.longitude.text = event.longitude.toString()
        Picasso.get().load(event.image).into(holder.image)

    }

    fun submitList(setList: List<Event>) {
        events = setList
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class ViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<View>(R.id.picture) as CircleImageView
        var title = itemView.findViewById<View>(R.id.title_item) as TextView
        var description = itemView.findViewById<View>(R.id.description_item) as TextView
        var longitude = itemView.findViewById<View>(R.id.longitude) as TextView
        var latitude = itemView.findViewById<View>(R.id.latitude) as TextView
        var price = itemView.findViewById<View>(R.id.price_item) as TextView
        var date = itemView.findViewById<View>(R.id.date_item) as TextView
    }
}
