package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

val CUSTOMDIENUM = "customdienumber"

class DieFragment : Fragment() {
    // for retaining die number
    private val CURRENTDIENUM = "currentdienum"
    private var dienum = 0

    lateinit var dieTextView: TextView

    var dieSides: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(CUSTOMDIENUM).run {
                dieSides = this
            }
        }
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

        // retain num
        // if savedInstanceState is not null, program been here before
        savedInstanceState?.run {
            dienum = getInt(CURRENTDIENUM, 0)
        }
        if (dienum == 0) {
            throwDie()
        } else {
            dieTextView.text = dienum.toString()
        }
    }

    fun throwDie() {
        // nextInt() get 0 to param-1
        dienum = (Random.nextInt(dieSides) + 1)
        dieTextView.text = dienum.toString()
    }

    // to retain instance when rotating
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // store previous data
        outState.putInt(CURRENTDIENUM, dienum)
    }

    // LAB ACTIVITY
    // accept the number sides the die should have
    companion object {

        fun newInstance (sides : Int) : DieFragment {
            val fragment = DieFragment()
            fragment.arguments = Bundle().apply {
                putInt(CUSTOMDIENUM, sides)
            }
            return fragment
        }
    }
}