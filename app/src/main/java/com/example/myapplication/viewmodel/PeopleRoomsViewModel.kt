package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repository.PeopleRoomsRepository
import com.example.myapplication.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleRoomsViewModel @Inject constructor(private val repository: PeopleRoomsRepository) : ViewModel() {

    private val _peopleList = MutableLiveData<UIState>()
    val peopleList: LiveData<UIState> get() = _peopleList

    private val _roomsLists :MutableLiveData<UIState> = MutableLiveData()
    val roomsLists : LiveData<UIState> get() = _roomsLists

    init {
        getPeopleLists()
        getRoomLists()
    }
    fun getPeopleLists() {
       viewModelScope.launch {
            repository.fetchPeople().collect {
                _peopleList.postValue(it)
            }
        }

    }

 fun getRoomLists(){
        viewModelScope.launch {
            repository.fetchRoomsDetails().collect{
                _roomsLists.postValue(it)
            }
        }
    }
}

