package com.example.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.ui.main.MainFragment
import com.example.movieapp.ui.main.SearchMovieFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_search_item -> changeFragment(SearchMovieFragment.newInstance())
            R.id.main_menu_home_item -> changeFragment(MainFragment.newInstance())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(newFragment: Fragment) {
        if (newFragment.isVisible)
            return
        supportFragmentManager.beginTransaction().replace(R.id.container, newFragment).commit()
    }
}