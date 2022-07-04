package com.example.myapplication.viewmodel



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.Repository.PeopleRoomsRepository
import com.example.myapplication.api.ApiService
import com.example.myapplication.model.people.PeopleModelItem
import com.example.myapplication.util.UIState
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class PeopleRoomsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()


    private lateinit var viewModel: PeopleRoomsViewModel
    @MockK
    lateinit var apiService: ApiService
    @MockK
    lateinit var repository: PeopleRoomsRepository
     lateinit var  peopleList:PeopleModelItem

    @Mock
    var observer: Observer<PeopleModelItem>? = null
    private val mainThreadSurrogate = newSingleThreadContext("MainThread")
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)

    }

    @Test
    suspend fun testNull() {
        `when`(apiService.getAllPeople()).thenReturn(null)
        assertNotNull(viewModel.getPeopleLists())
        assertTrue(viewModel.peopleList.hasObservers())
    }
//
//    @Test
//    fun testApiFetchDataSuccess() {
//        // Mock API response
//        `when`(apiService.fetchNews()).thenReturn(Single.just(NewsList()))
//        viewModel.getPeopleLists()
//        verify(observer).onChanged(UIState.Loading())
//        verify(observer).onChanged(UIState.Success)
//    }

//    @Test
//    fun testApiFetchDataError() {
//        `when`(apiService.fetchNews()).thenReturn(Single.error(Throwable("Api error")))
//        viewModel.fetchNews()
//        verify(observer).onChanged(NewsListViewState.LOADING_STATE)
//        verify(observer).onChanged(NewsListViewState.ERROR_STATE)
//    }

    fun `checking that people list have some data`(){
        //initialize view model
        viewModel= PeopleRoomsViewModel(repository)
        peopleList=PeopleModelItem("picture","today","poo@gmain.com","red"
        ,"Mirella","1","CDM","Scott",1.1,1.2,"4334242")
        assertNotNull(peopleList)
        //assertTrue(peopleList.isNullOrEmpty())
        val containData =false

    }

//    @After
//    @Throws(Exception::class)
//    fun tearDown() {
//        apiService = null
//        viewModel = null
//    }

}