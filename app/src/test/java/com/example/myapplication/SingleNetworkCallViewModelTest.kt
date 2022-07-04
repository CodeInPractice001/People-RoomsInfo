package com.example.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.Repository.PeopleRoomsRepository
import com.example.myapplication.api.ApiService
import com.example.myapplication.model.people.PeopleModel
import com.example.myapplication.model.people.PeopleModelItem

import com.example.myapplication.util.UIState
import com.example.myapplication.viewmodel.PeopleRoomsViewModel
import io.mockk.impl.annotations.MockK

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiService

    @Mock
    lateinit var repository: PeopleRoomsRepository


    @Mock
    private lateinit var apiUsersObserver: Observer<UIState.Success<List<PeopleModelItem>>>

    @Before
    fun setUp() {

    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<PeopleModel>())
                .`when`(apiHelper)
                .getAllPeople()
            val viewModel = PeopleRoomsViewModel(repository =repository )
       //    viewModel.getPeopleLists()
         viewModel.peopleList.getOrAwait()
            verify(apiHelper).getAllPeople()
            verify(apiUsersObserver).onChanged(UIState.Success(emptyList()))
            viewModel.peopleList
        }
    }

//    @Test
//    fun givenServerResponseError_whenFetch_shouldReturnError() {
//        testCoroutineRule.runBlockingTest {
//            val errorMessage = "Error Message For You"
//            doThrow(RuntimeException(errorMessage))
//                .`when`(apiHelper)
//                .getUsers()
//            val viewModel = PeopleViewModel()
//            viewModel.getUsers().observeForever(apiUsersObserver)
//            verify(apiHelper).getUsers()
//            verify(apiUsersObserver).onChanged(
//                Resource.error(
//                    RuntimeException(errorMessage).toString(),
//                    null
//                )
//            )
//            viewModel.getUsers().removeObserver(apiUsersObserver)
//        }
//    }

    @After
    fun tearDown() {
        // do something if required
    }

}