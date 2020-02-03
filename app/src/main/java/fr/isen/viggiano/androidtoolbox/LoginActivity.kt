package fr.isen.viggiano.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE)
        if(sharedPreferences.getString("Login"," ") != " "){
            val changePage = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(changePage)
        }
        var button1 = findViewById<Button>(R.id.loginButton)
        button1.setOnClickListener(){
            var username:String = identifiant.text.toString()
            var pass:String = password.text.toString()
            if(username=="admin"&&pass=="123") {
                val message = "Hello $username"
                val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
                toast.show()
                val monIntent : Intent =  Intent(this,HomeActivity::class.java)
                startActivity(monIntent)
            }
            else {
                Toast.makeText(applicationContext, "Authentification échouée", Toast.LENGTH_LONG).show()
            }
        }
    }
}