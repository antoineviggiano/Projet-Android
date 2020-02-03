package fr.isen.viggiano.androidtoolbox


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class LifeCycleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationFragment("onCreate")
        return inflater.inflate(R.layout.life_cycle_fragment, container, false)
    }

    private fun notificationFragment(message: String) {
        Log.d("TAG", message)
        Toast.makeText(activity, "Fragment 1 : $message", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        notificationFragment("onStart")
    }

    override fun onResume() {
        super.onResume()
        notificationFragment("onResume")
    }

    override fun onPause() {
        super.onPause()
        notificationFragment("onPause")
    }

    override fun onStop() {
        super.onStop()
        notificationFragment("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationFragment("onDestroy")
    }
}