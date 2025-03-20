package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DieViewModel : ViewModel() {
    // val because data inside changes but livedata is the same
    //      to observe data changes   v                 v delay instantiation; create only when needed
    private val currentRoll : MutableLiveData<Int> by lazy {
        //                                         ^ operations performed by something else
        MutableLiveData()
    }

    // get approach            v don't want data to be changed
    fun getCurrentRoll() : LiveData<Int> {
        return currentRoll
    }

}