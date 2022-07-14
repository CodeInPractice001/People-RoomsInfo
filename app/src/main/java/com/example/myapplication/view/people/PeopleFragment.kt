package com.example.myapplication.view.people

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.databinding.FragmentPeopleBinding
import com.example.myapplication.model.people.PeopleModelItem
import com.example.myapplication.util.UIState
import com.example.myapplication.viewmodel.FragmentsViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PeopleFragment : FragmentsViewModel() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var peopleAdapter: RecyclerViewAdapter

    private val binding: FragmentPeopleBinding by lazy {
        FragmentPeopleBinding.inflate(layoutInflater)
    }

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
        peopleAdapter = RecyclerViewAdapter(openDesc = ::openDialog)
        binding.rvPeople.adapter = peopleAdapter
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.peopleList.observe(viewLifecycleOwner) { state ->

            when (state) {
                is UIState.Success<*> -> {
                    binding.apply {
                        progress.visibility = View.GONE

                        val people = state.response

                        peopleAdapter.setLists(people as List<PeopleModelItem>)

                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        progress.visibility = View.GONE
                        tvError.text = state.exception.message
                    }
                }
                else -> {}
            }
        }


    }

    @SuppressLint("SetTextI18n")
    private fun openDialog(peopleItem: PeopleModelItem) {

        val alertDialog: AlertDialog
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.people_description, null)

        val image: ImageView = view.findViewById(R.id.imageView)
        val colorImageView: ImageView = view.findViewById(R.id.ivColor)
        val txtPhone: TextView = view.findViewById(R.id.tvDetailedPhone)
        val txtColor: TextView = view.findViewById(R.id.tvDetailedColor)
        val txtName: TextView = view.findViewById(R.id.tvDetailedName)
        val txtJob: TextView = view.findViewById(R.id.tvDetailedJobTitle)
        val txtLat: TextView = view.findViewById(R.id.tvDetailedLat_)
        val txtLong: TextView = view.findViewById(R.id.tvDetailedLong)
        val txtEmail: TextView = view.findViewById(R.id.tvDetailedEmail)

     //   txtColor.setBackgroundColor(Color.parseColor(peopleItem.favouriteColor))
        txtPhone.text = peopleItem.phone
        txtColor.text = peopleItem.favouriteColor
        Glide.with(requireActivity()).load(peopleItem.avatar).into(image)
        txtEmail.text = peopleItem.email
        txtJob.text = peopleItem.jobTitle
        txtLong.text = peopleItem.longitude.toString()
        txtLat.text = peopleItem.latitude.toString()
        txtName.text = peopleItem.firstName + " " + peopleItem.lastName


colorImageView.setBackgroundColor(Color.parseColor(peopleItem.favouriteColor))


        builder.setView(view)
        alertDialog = builder.create()
        alertDialog.show()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PeopleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PeopleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}