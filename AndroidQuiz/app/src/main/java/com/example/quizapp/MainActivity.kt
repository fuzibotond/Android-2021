package com.example.quizapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.quizapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() { //,Communicator
    private val TAG = "MainActivity"
    private lateinit var topAppBar:MaterialToolbar
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navigationView:NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
        initializeView()
        initMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    private fun initializeView(){
        topAppBar = findViewById(R.id.topAppBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.menu_navigation_view)
    }
    private fun initMenu(){
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()

        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.home -> findNavController(R.id.nav_host_fragment).navigate(R.id.quizStartFragment)
            }
            drawerLayout.close()
            true
        }
    }


    override fun onPause() {
        super.onPause()
        val text = "We are on pause..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun onStop() {
        super.onStop()
        val text = "We are on stop..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        val text = "We are on destroy..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun onRestart() {
        super.onRestart()
        val text = "We are on restart..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

//    override fun passDataCom(editTextInput: String) {
//        val bundle = Bundle()
//        bundle.putString("message", editTextInput)
//
//        val transaction =this.supportFragmentManager.beginTransaction()
//        val questionFragment = QuestionFragment()
//        questionFragment.arguments = bundle
//        transaction.replace(R.id.fragmentContainerView,questionFragment)
//        transaction.commit()
//    }


}