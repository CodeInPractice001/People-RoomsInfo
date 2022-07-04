package com.example.myapplication

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.view.people.PeopleFragment
import com.example.myapplication.view.rooms.RoomsFragment

class ViewPagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val TAB_TITLTES= arrayListOf(R.string.People,R.string.Rooms)
    override fun getCount(): Int {
        return 2
    }
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> PeopleFragment()
            1 -> RoomsFragment()
            else -> {
                PeopleFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLTES[position])
    }
}