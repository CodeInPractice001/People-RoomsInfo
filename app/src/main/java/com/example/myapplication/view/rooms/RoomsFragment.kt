package com.example.myapplication.view.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.databinding.FragmentRoomsBinding
import com.example.myapplication.model.RoomsModelItem

import com.example.myapplication.model.people.PeopleModelItem
import com.example.myapplication.util.UIState
import com.example.myapplication.viewmodel.FragmentsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RoomsFragment : FragmentsViewModel() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding: FragmentRoomsBinding by lazy {
        FragmentRoomsBinding.inflate(layoutInflater)
    }
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recyclerViewAdapter = RecyclerViewAdapter(openDesc = ::openDesc)
        binding.rvRooms.adapter = recyclerViewAdapter
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {

        viewModel.roomsLists.observe(viewLifecycleOwner) { state ->
            when (state) {

                is UIState.Success<*> -> {
                    binding.apply {
                        RoomProgressbar.visibility = View.GONE

                        val rooms = state.response
                        //recyclerViewAdapter.setRoomList(rooms as List<RoomsModelItem>)
                        recyclerViewAdapter.setRoomList(rooms as List<RoomsModelItem>)

                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        RoomProgressbar.visibility = View.GONE
                        tvRoomError.text = state.exception.message
                    }
                }
                is UIState.Loading -> {
                   // binding.RoomProgressbar.visibility = View.GONE
                   // binding.tvRoomError.text = "Failed Network Call!"
                }
            }
        }
    }

 private   fun openDesc(rooms: PeopleModelItem){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoomsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoomsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}