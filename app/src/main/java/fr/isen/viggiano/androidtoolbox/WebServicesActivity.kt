package fr.isen.viggiano.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class WebServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)


        var name = findViewById<TextView>(R.id.nameText)
        var address = findViewById<TextView>(R.id.addressText)
        var email = findViewById<TextView>(R.id.emailText)
        var photo = findViewById<ImageView>(R.id.imageView)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?inc=name,location,email,picture"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 2000 characters of the response string.
                var randomUser=Gson().fromJson(response.substring(0, 2000), RandomUser::class.java)
                Log.d("tag",randomUser.results[0].email)
                name.text = randomUser.results[0].name.first + " " + randomUser.results[0].name.last
                //address.text= randomUser.results[0].location.street.number
            },
            Response.ErrorListener { Log.d("tag","That didn't work!") })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)


    }
}
