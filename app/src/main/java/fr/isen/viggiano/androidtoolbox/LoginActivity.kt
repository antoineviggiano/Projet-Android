package fr.isen.viggiano.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var valider = findViewById<Button>(R.id.valider)
        valider.setOnClickListener(){
            Toast.makeText(applicationContext, "this is toast message", Toast.LENGTH_SHORT).show()
            val toast = Toast.makeText(applicationContext, "Hello Javatpoint", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
