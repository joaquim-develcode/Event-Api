package com.example.events.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.events.R
import com.example.events.model.Event
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class EventsAdapter(private var items: List<Event>) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event: Event = items[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = items.size

    class EventViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_event, parent, false)) {
        private var image: CircleImageView? = null
        var title: TextView? = null
        var description: TextView? = null
        var longitude: TextView? = null
        var latitude: TextView? = null
        var price: TextView? = null
        var date: TextView? = null

        init {
            image = itemView.findViewById<View>(R.id.picture) as CircleImageView
            title = itemView.findViewById<View>(R.id.title_item) as TextView
            description = itemView.findViewById<View>(R.id.description_item) as TextView
            longitude = itemView.findViewById<View>(R.id.longitude) as TextView
            latitude = itemView.findViewById<View>(R.id.latitude) as TextView
            price = itemView.findViewById<View>(R.id.price_item) as TextView
            date = itemView.findViewById<View>(R.id.date_item) as TextView
        }

        fun bind(event: Event) {
            title?.text = event.title
            description?.text = event.description
            longitude?.text = event.longitude.toString()
            latitude?.text = event.latitude.toString()
            price?.text = event.price.toString()
            date?.text = event.date.toString()
            Picasso.get().load(event.image).into(image)
        }
    }

}
