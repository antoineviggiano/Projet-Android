package fr.isen.viggiano.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var sharedPreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE)
        var buttonDeconnexion = findViewById<Button>(R.id.deconnexionButton)
        buttonDeconnexion.setOnClickListener {
            var editor = sharedPreferences.edit()
            editor.putString("Login", " ")
            editor.apply()

            var intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        var buttonLifeCycle = findViewById<ImageButton>(R.id.lifeCycleImage)
        buttonLifeCycle.setOnClickListener(){
            val monIntent : Intent =  Intent(this,LifeCycleActivity::class.java)
            startActivity(monIntent)
        }
        var buttonSave = findViewById<ImageButton>(R.id.saveImage)
        buttonSave.setOnClickListener(){
            val monIntent : Intent =  Intent(this,FormActivity::class.java)
            startActivity(monIntent)
        }
        var buttonPermissions = findViewById<ImageButton>(R.id.permissionsImage)
        buttonPermissions.setOnClickListener(){
            val monIntent : Intent =  Intent(this,PermissionsActivity::class.java)
            startActivity(monIntent)
        }
        var buttonWebServices = findViewById<ImageButton>(R.id.webServicesImage)
        buttonWebServices.setOnClickListener(){
            val monIntent : Intent =  Intent(this,WebServicesActivity::class.java)
            startActivity(monIntent)
        }
    }
}
