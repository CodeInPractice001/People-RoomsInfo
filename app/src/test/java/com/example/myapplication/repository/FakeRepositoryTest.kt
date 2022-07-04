package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Repository.PeopleRoomsRepository
import com.example.myapplication.model.people.PeopleModel
import com.example.myapplication.util.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryTest:PeopleRoomsRepository{

    private val peopleItem = mutableListOf<PeopleModel>()
    private val peopleLiveData = MutableLiveData<UIState>()
    private val roomsLiveData = MutableLiveData<UIState>()


    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }
    override suspend fun fetchPeople(): Flow<UIState> =
        flow {
//emit(UIState.Success<*>)
        }



    override suspend fun fetchRoomsDetails(): Flow<UIState> {
        TODO("Not yet implemented")
    }

}