package fr.isen.viggiano.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity() {

    private val url = "https://randomuser.me/api/?results=15&inc=name,location,email,picture"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, Response.Listener {
                response ->
            val randomUser = Gson().fromJson(response.toString(), RandomUser::class.java)
            user_recycler_view.layoutManager = LinearLayoutManager(this)
            user_recycler_view.adapter = MyAdapter(randomUser,this)
        }, Response.ErrorListener {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG)
        })
        queue.add(request)
    }
}