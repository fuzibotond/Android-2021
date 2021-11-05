package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.updateLayoutParams
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.quizapp.models.SharedViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var topAppBar:MaterialToolbar
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navigationView:NavigationView
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
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
        sharedViewModel.saveName("Sign in")
    }
    private fun initMenu(){
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
            val header = navigationView.getHeaderView(0)
            val nameTextViev:TextView = header.findViewById(R.id.name)
            nameTextViev.setText(sharedViewModel.name.value)
            val subNameTextView:TextView = header.findViewById(R.id.sub_name)
            subNameTextView.setText(sharedViewModel.name.value)

            val imgProfilePic:ImageView = header.findViewById(R.id.avatar)
            if (imgProfilePic != null){
                imgProfilePic.setImageBitmap(sharedViewModel.imgProfilePic.value)
            }
        }


        navigationView.setNavigationItemSelectedListener { menuItem ->

            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.home -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                R.id.quiz_time -> findNavController(R.id.nav_host_fragment).navigate(R.id.quizStartFragment)
                R.id.profile -> findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                R.id.list_of_questions -> findNavController(R.id.nav_host_fragment).navigate(R.id.questionListFragment)
                R.id.new_question -> findNavController(R.id.nav_host_fragment).navigate(R.id.questionAddFragment)
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