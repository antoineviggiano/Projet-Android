package fr.isen.viggiano.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_web_services.*
import kotlinx.android.synthetic.main.random_user_view.*

class WebServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)


        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?inc=name,location,email,picture"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var randomUser =
                    Gson().fromJson(response.toString(), RandomUser::class.java)
                user_recycler_view.layoutManager = LinearLayoutManager(this)

                user_recycler_view.adapter = MyAdapter(randomUser,this)
                var name: String =
                    randomUser.results[0].name.first + " " + randomUser.results[0].name.last
                var address: String =
                    randomUser.results[0].location.street.number.toString() + " " + randomUser.results[0].location.street.name + " " + randomUser.results[0].location.city + " " + randomUser.results[0].location.state + " " + randomUser.results[0].location.country + " " + randomUser.results[0].location.postcode
                var email: String = randomUser.results[0].email
                var pictureLink: String = randomUser.results[0].picture.large
                //Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(picture)
            },
            Response.ErrorListener { Log.d("tag", "That didn't work!") })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}
