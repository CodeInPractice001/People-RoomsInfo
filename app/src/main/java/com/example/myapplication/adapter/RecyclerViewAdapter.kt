package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.PeopleListBinding
import com.example.myapplication.databinding.RoomsListBinding
import com.example.myapplication.model.RoomsModelItem
import com.example.myapplication.model.people.PeopleModelItem

const val PEOPLE = 0
const val ROOMS = 1

class RecyclerViewAdapter(private val openDesc: (PeopleModelItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mPeople: MutableList<PeopleModelItem> = mutableListOf()
    private var mRooms: MutableList<RoomsModelItem> = mutableListOf()

    fun setLists(list: List<PeopleModelItem>) {
        mPeople.clear()
        mPeople.addAll(list)
        notifyDataSetChanged()
    }

    fun setRoomList(list: List<RoomsModelItem>) {
        mRooms.clear()
        mRooms.addAll(list)
        notifyDataSetChanged()
    }

    inner class PeopleViewHolder(private val binding: PeopleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(peopleItem: PeopleModelItem) {
            Glide.with(binding.ivImage).load(peopleItem.avatar).into(binding.ivImage)
            binding.txtEmail.text = peopleItem.email
            binding.txtName.text = peopleItem.firstName
            binding.txtOccupation.text = peopleItem.jobTitle
            binding.root.setOnClickListener { openDesc(peopleItem) }

        }
    }

    inner class RoomViewHolder(private val binding: RoomsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rooms: RoomsModelItem) {
            val switch = rooms.is_occupied
            var data: String = rooms.created_at
            binding.apply {
                tvCreatedAt.text = data.substring(0, 10)
                tvOcupancy.text = rooms.max_occupancy.toString()
                tvRoomId.text = rooms.id
                tvRoomName.text = rooms.name
                RoomSwitch.isChecked = switch == true

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == PEOPLE) {
            return PeopleViewHolder(
                PeopleListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        if (viewType == ROOMS) {
            return RoomViewHolder(
                RoomsListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return PeopleViewHolder(
            PeopleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        if (position < mPeople.size) {
            return PEOPLE
        }
        if (position - mPeople.size < mRooms.size) {
            return ROOMS
        }
        return -1
    }


    override fun getItemCount(): Int {
        return mPeople.size + mRooms.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PeopleViewHolder) {
            holder.bind(mPeople[position])
        }
        if (holder is RoomViewHolder) {
            holder.bind(mRooms[position])
        }
    }

}