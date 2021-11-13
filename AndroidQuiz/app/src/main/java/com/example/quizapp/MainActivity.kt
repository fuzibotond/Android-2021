package com.example.quizapp

import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.updateLayoutParams
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.quizapp.databinding.HeaderNavigationDrawerBinding
import com.example.quizapp.models.SharedViewModel
import com.example.quizapp.module.Answer
import com.example.quizapp.module.Question
import com.example.quizapp.repository.Repository
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var topAppBar:MaterialToolbar
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navigationView:NavigationView
    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var viewModel: MainViewModel


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
        initializeView()
        initMenu()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()

        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                var questions = arrayListOf<Question>()
                var categories = mutableMapOf<String, MutableList<Question>>()
                Log.d("Response", response?.body()?.response_code.toString())
                response.body()?.results?.forEach {
                    val answers = arrayListOf<Answer>()
                    it.incorrect_answers.forEach { answers.add(Answer(it, false)) }
                    answers.add(Answer(it.correct_answer,true))
                    answers.shuffle()
                    questions.add(Question(it.question, answers))

//                    categories.putIfAbsent(it.category, mutableListOf(Question(it.question, answers)))
                }
                categories.put("Any category", questions)
                response.body()?.results?.forEach { categories.putIfAbsent(it.category, mutableListOf()) }
                response.body()?.results?.forEach {
                    val answers = arrayListOf<Answer>()
                    it.incorrect_answers.forEach { answers.add(Answer(it, false)) }
                    answers.add(Answer(it.correct_answer,true))
                    answers.shuffle()
//                    questions.add(Question(it.question, answers))
                    categories[it.category]?.add(Question(it.question,answers))

                }
                sharedViewModel.saveCategories(categories)
                sharedViewModel.saveQuestions(questions)
                response.body()?.results?.size?.let { sharedViewModel.saveSize(it) }
                if (sharedViewModel.questions.value != null){
                    sharedViewModel.questions.value?.forEach {
                        Log.d("saved", it.text)
                    }
                }
            }
        })


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
            val nickName:TextView = header.findViewById(R.id.name)
            val subName:TextView = header.findViewById(R.id.sub_name)

            val nameToCheck = sharedViewModel.name.value!!
            if (nameToCheck.contains(' ')){
                Toast.makeText(this, "$nameToCheck", Toast.LENGTH_SHORT).show()
                val name = sharedViewModel.name.value!!.takeWhile { it != ' ' }
                nickName.setText(name)
                subName.setText(sharedViewModel.name.value)
            }
            else{
                nickName.setText(sharedViewModel.name.value)
                subName.setText(sharedViewModel.name.value)
            }

            val imgProfilePic:ImageView = header.findViewById(R.id.avatar)
            imgProfilePic.setImageBitmap(sharedViewModel.imgProfilePic.value)

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