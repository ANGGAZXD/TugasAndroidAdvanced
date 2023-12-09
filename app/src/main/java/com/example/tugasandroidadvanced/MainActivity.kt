package com.example.tugasandroidadvanced

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.tugasandroidadvanced.Fragment.Alarm.AlarmFragment
import com.example.tugasandroidadvanced.Fragment.Game.GameFragment
import com.example.tugasandroidadvanced.Fragment.Home.HomeFragment
import com.example.tugasandroidadvanced.Fragment.Profile.ProfileFragment
import com.example.tugasandroidadvanced.Fragment.Retrofit.RetrofitFragment
import com.example.tugasandroidadvanced.Fragment.Setting.SettingFragment
import com.example.tugasandroidadvanced.Fragment.TabLayout.Tab1Fragment
import com.example.tugasandroidadvanced.Fragment.TabLayout.Tab2Fragment
import com.example.tugasandroidadvanced.Fragment.TabLayout.Tab3Fragment
import com.example.tugasandroidadvanced.Fragment.WorkManager.WorkFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvDisplay = findViewById<TextView>(R.id.tvDisplay)
        val sharePreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

        val username = sharePreference.getString("USERNAME", "").toString()
        val password = sharePreference.getString("PASSWORD", "").toString()
        tvDisplay.text = "Username: $username and Password: $password"


        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> replaceFragment(HomeFragment())
                R.id.bottom_profile -> replaceFragment(ProfileFragment())
                R.id.bottom_game -> replaceFragment(GameFragment())
                R.id.bottom_work -> replaceFragment(WorkFragment())
                R.id.bottom_retrofit -> replaceFragment(RetrofitFragment())
            }
            true
        }

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Ini adalah Toolbar"
        setSupportActionBar(toolbar)

        // Menggunakan findViewById untuk mendapatkan referensi TabLayout dan ViewPager
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        // Inisialisasi TabLayout dan ViewPager
        tabLayout.setupWithViewPager(viewPager)

        // Inisialisasi dan pengaturan adapter untuk ViewPager
        val tabLayoutAdapter = TabLayoutAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        tabLayoutAdapter.addFragment(Tab1Fragment(), "Tabe1")
        tabLayoutAdapter.addFragment(Tab2Fragment(), "Tabe2")
        tabLayoutAdapter.addFragment(Tab3Fragment(), "Tabe3")
        viewPager.adapter = tabLayoutAdapter

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val animatedImageView: ImageView = findViewById(R.id.animatedImageView)

        val rotationAnimator = ObjectAnimator.ofFloat(animatedImageView, "rotation", 0f, 360f)
        rotationAnimator.duration = 20000
        rotationAnimator.interpolator = AccelerateDecelerateInterpolator()
        rotationAnimator.start()

        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_draw_home -> replaceFragment(HomeFragment())
                R.id.nav_draw_aboutUs -> showToast("Clicked About Us")
                R.id.nav_draw_help -> showToast("Clicked Help")
                R.id.nav_draw_profile -> replaceFragment(ProfileFragment())
                R.id.nav_draw_setting -> replaceFragment(SettingFragment())
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}
