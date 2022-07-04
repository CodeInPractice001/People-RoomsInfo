package com.example.myapplication.Repository

import com.example.myapplication.api.ApiService
import com.example.myapplication.util.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject


interface PeopleRoomsRepository{
    suspend fun fetchPeople(): Flow<UIState>
    suspend fun fetchRoomsDetails():Flow<UIState>
}
class PeopleRepositoryImp @Inject constructor(private val apiService: ApiService):PeopleRoomsRepository
{
    override suspend fun fetchPeople(): Flow<UIState> =
       flow {
          emit(UIState.Loading)
          try{
              val response = apiService.getAllPeople()
              if(response.isSuccessful){
                  response.body()?.let {
                      emit(UIState.Success(it))
                  }?:throw Exception("Response is null")
              }
              else throw Exception("Failed Network Call")
          }catch(e :Exception){
              emit(UIState.Error(e))
          }
       }

    override suspend fun fetchRoomsDetails(): Flow<UIState> =
        flow{
            emit(UIState.Loading)
            try{
                val response = apiService.getAllRoom()
                if(response.isSuccessful){
                    response.body()?.let{
                        emit(UIState.Success(it))
                    }?:throw Exception("Response is null")
                }
                else throw Exception("Network Failed")
            }catch (e :Exception){
                emit(UIState.Error(e))
            }
        }


}