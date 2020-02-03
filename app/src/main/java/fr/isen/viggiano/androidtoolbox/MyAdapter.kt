package fr.isen.viggiano.androidtoolbox


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(private val items: RandomUser, val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolderRandom>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderRandom {

        return ViewHolderRandom(
            LayoutInflater.from(context).inflate(
                R.layout.random_user_view,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return items.results.size
    }

    override fun onBindViewHolder(holder: ViewHolderRandom, position: Int) {

        holder.randomUserName.text =
            items.results[position].name.first + " " + items.results[position].name.last
        holder.randomUserAddress.text =
            items.results[position].location.street.number.toString() + " " +
                    items.results[position].location.street.name + ", " + items.results[position].location.city + ", " +
                    items.results[position].location.country
        holder.randomUserEmail.text = items.results[position].email
        Picasso.with(context)
            .load(items.results[position].picture.large)
            .into(holder.randomUserPict)
    }

    class ViewHolderRandom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val randomUserName: TextView = itemView.findViewById(R.id.name)
        val randomUserAddress: TextView = itemView.findViewById(R.id.address)
        val randomUserEmail: TextView = itemView.findViewById(R.id.email)
        val randomUserPict: ImageView = itemView.findViewById(R.id.picture)
    }
}