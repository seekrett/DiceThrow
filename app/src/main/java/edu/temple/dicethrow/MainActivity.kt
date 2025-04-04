package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment())
                .commit()
        }

        // safe to do when fragment is added during design time but not run time
        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            // access function in DieFragment.kt from outside
            // grab reference to that fragment
            (supportFragmentManager
                // returns a fragment instance           treated as a DieFragment.kt
                .findFragmentById(R.id.fragmentContainerView) as DieFragment).throwDie()
        }
    }
}