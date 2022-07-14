package com.example.myapplication


import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.people.PeopleFragment
import com.example.myapplication.view.rooms.RoomsFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
   lateinit var  sectionsPagerAdapter :ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //supportActionBar?.hide()
         sectionsPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        val drawerLayout: DrawerLayout = binding.mainActivity
        val navView: NavigationView = binding.navView

        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.Open,R.string.Close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        tabs.getTabAt(0)?.setIcon(R.drawable.people)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_baseline_home_24)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.nav_home -> {
                   // val fragment = PeopleFragment()

                val   transaction :   FragmentTransaction =supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.view_pager, sectionsPagerAdapter.getItem(0))
                    transaction.commit()
                    drawerLayout.closeDrawer(GravityCompat.START)

                }
                R.id.nav_room -> {
                    val fragment : RoomsFragment = sectionsPagerAdapter.getItem(1) as RoomsFragment
                    val   transaction :   FragmentTransaction =supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.view_pager,fragment)
                    transaction.commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_faq -> Toast.makeText(this, "FAQ", Toast.LENGTH_SHORT).show()
                R.id.share -> Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()

            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

//    fun openFrag() {
////        val manager: FragmentManager = supportFragmentManager
////        val transaction = manager.beginTransaction()
////        transaction.replace(R.id.container, YOUR_FRAGMENT_NAME, YOUR_FRAGMENT_STRING_TAG)
////        transaction.addToBackStack(null)
////        transaction.commit()
//}
}