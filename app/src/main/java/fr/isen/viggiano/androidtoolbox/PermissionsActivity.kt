package fr.isen.viggiano.androidtoolbox

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_permissions.*



abstract class PermissionsActivity : AppCompatActivity(), LocationListener {
    override fun onLocationChanged(location: Location?) {
        showCurrentPosition()
    }

    private var locationManager: LocationManager? = null

    var array = arrayOf(
        "Contact 1",
        "Contact 2",
        "Contact 3",
        "Contact 4",
        "Contact 5",
        "Contact 6",
        "Contact 7",
        "Contact 8",
        "Contact 9",
        "Contact 10",
        "Contact 11",
        "Contact 12",
        "Contact 13",
        "Contact 14",
        "Contact 15",
        "Contact 16",
        "Contact 17",
        "Contact 18",
        "Contact 19",
        "Contact 20"
    )

    companion object {
        private const val IMAGE_PICK_REQUEST = 1000
        private const val CAMERA_PICK_REQUEST = 4444
        private const val CONTACT_PICK_REQUEST = 1001

        const val PERMISSION_CODE = 1002
    }


    private fun showCurrentPosition() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 1f, this)
            val location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            val lat = location?.latitude.toString()
            latText.text = "Latitude : $lat"
            val long = location?.longitude.toString()
            longText.text = "Longitude : $long"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        val adapter = ArrayAdapter(this, R.layout.listview_item, array)
        val listView: ListView = findViewById(R.id.contactList)
        listView.adapter = adapter

        photoButton.setOnClickListener {
            withItems()
        }
    }

    override fun onStop() {
        super.onStop()
        locationManager?.removeUpdates(this)
    }

    fun imageFromGallery() {
        val intent: Intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        startActivityForResult(intent, IMAGE_PICK_REQUEST)
    }

    fun imageFromCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, CAMERA_PICK_REQUEST)
            }
        }
    }

    fun withItems() {

        val items = arrayOf("Prendre une photo", "Galerie")
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Choisir : ")
            setItems(items) { _, which ->
                if (items[which] == "Prendre une photo") {
                    imageFromCamera()
                } else {
                    imageFromGallery()
                }
            }

            setPositiveButton("Retour", backButtonClick)
            show()
        }
    }

    private val backButtonClick = { _: DialogInterface, _: Int ->
        Toast.makeText(applicationContext, "Aucune photo choisie", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_REQUEST) {
            photoButton.setImageURI(data?.data)
        } else if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_PICK_REQUEST) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            photoButton.setImageBitmap(imageBitmap)
        }
    }
}
