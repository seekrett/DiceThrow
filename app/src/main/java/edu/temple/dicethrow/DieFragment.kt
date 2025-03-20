package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {
    /*
    removed:
    private val CURRENTDIENUM = "currentdienum"
    private var dienum = 0
     */
    var dieSides: Int = 6

    val DIESIDE = "sidenumber"

    lateinit var dieTextView: TextView

    lateinit var dieViewModel: DieViewModel

    // state information
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
        dieViewModel = ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //                               my lifecycle v
        dieViewModel.getCurrentRoll().observe(viewLifecycleOwner) {
            dieTextView.text = it.toString()
        }

        if (dieViewModel.getCurrentRoll().value == null) {
            dieViewModel.rollDie()
        }
    }

    // removed onSavedInstanceState
}