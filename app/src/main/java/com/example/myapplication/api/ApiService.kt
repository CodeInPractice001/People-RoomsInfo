package com.example.myapplication.api

import com.example.myapplication.model.RoomsModelItem

import com.example.myapplication.model.people.PeopleModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("v1/people")
    suspend fun getAllPeople(): Response<PeopleModel>

    @GET("v1/rooms")
    suspend fun getAllRoom(): Response<List<RoomsModelItem>>
}