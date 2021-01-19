package com.internetbrands.whattoeat.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.internetbrands.whattoeat.R
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.bean.Week
import com.internetbrands.whattoeat.ui.fragment.CookMenuFragment
import com.internetbrands.whattoeat.ui.fragment.WeekMenuFragment
import com.internetbrands.whattoeat.util.Constants
import com.internetbrands.whattoeat.util.Utils
import com.internetbrands.whattoeat.util.injectViewModel
import com.internetbrands.whattoeat.viewmodel.MainViewModel
import com.internetbrands.whattoeat.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), OnNavigationItemSelectedListener {

    val TAG_COOKMENU = "cookMenu"
    val TAG_WEEKMENU = "weekMenu"

    @Inject
    lateinit var factory: MainViewModelFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var cookList: List<Cook>
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var cookMenuFragment: CookMenuFragment

    @Inject
    lateinit var weekMenuFragment: WeekMenuFragment
    lateinit var currentFragment: Fragment

    @Inject
    lateinit var dayMenu: DayMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = injectViewModel(factory)
        if (sharedPreferences.getBoolean(Constants.KEY_INIT, true)) {
            viewModel.insertCookList(cookList)
            viewModel.insertMenu(dayMenu)

            var editor = sharedPreferences.edit()
            editor.putBoolean(Constants.KEY_INIT, false)
            editor.commit()
        }

        initNavigation()

        currentFragment = weekMenuFragment

        replaceFragment(weekMenuFragment, TAG_WEEKMENU)

        addBtn.setOnClickListener {
            when (currentFragment) {
                cookMenuFragment -> cookMenuFragment.toAddCook()
                weekMenuFragment -> weekMenuFragment.toAddMenu()
            }
        }
    }

    private fun initNavigation() {
        navigationView.setNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var actionbardrawToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar, 0, 0
        )

        drawerLayout.addDrawerListener(actionbardrawToggle)
        actionbardrawToggle.syncState()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawers()
        toolbar.title = item.title
        when (item.itemId) {
            R.id.item_cook_list -> {
                replaceFragment(cookMenuFragment, TAG_COOKMENU)
            }
            R.id.item_menu_week -> {
                replaceFragment(weekMenuFragment, TAG_WEEKMENU)
            }
        }
        return true
    }

    fun replaceFragment(fragment: Fragment, tag: String) {

        // if there is no fragment add the fragment
        var instanceFragment = supportFragmentManager.findFragmentByTag(tag)
        if (instanceFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }

        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().show(fragment).commit()
        } else if (currentFragment != fragment) {
            supportFragmentManager.beginTransaction().hide(currentFragment).show(fragment).commit()
        }

        currentFragment = fragment

    }
}
