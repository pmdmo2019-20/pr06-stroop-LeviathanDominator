package es.iessaladillo.pedrojoya.stroop.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars

class Data: ViewModel() {

    private val _avatarList: MutableLiveData<List<Int>> = MutableLiveData(avatarList())
    val avatarList: LiveData<List<Int>>
        get() = _avatarList

    private fun avatarList(): List<Int> {
        return avatars
    }

}