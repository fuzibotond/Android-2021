package com.example.quizapp.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.quizapp.R
import com.example.quizapp.models.SharedViewModel

class ProfileFragment : Fragment() {
    lateinit var viewLayout:View
    lateinit var imgViewer:ImageView
    lateinit var etPlayerName:EditText
    lateinit var tvHighScore:TextView
    lateinit var tvPlayerName:TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var btnSave:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.fragment_profile, container, false)
        initialize()
        setListeners()
        return viewLayout
    }

    private fun initialize() {
        imgViewer = viewLayout.findViewById(R.id.img_profile)
        if (sharedViewModel.imgProfilePic.value != null){
            imgViewer.setImageBitmap(sharedViewModel.imgProfilePic.value)
        }
        tvHighScore = viewLayout.findViewById(R.id.high_score)
        etPlayerName = viewLayout.findViewById(R.id.player_name)
        tvPlayerName = viewLayout.findViewById(R.id.tv_player_name)
        tvPlayerName.setText("Your name: ")
        etPlayerName.setText( sharedViewModel.name.value.toString())
        tvHighScore.setText("High score: " + sharedViewModel.getScore() + " points")
        btnSave = viewLayout.findViewById(R.id.btn_save)
    }

    private fun setListeners(){
        btnSave.setOnClickListener {
            sharedViewModel.saveName(etPlayerName.text.toString())
        }
    }

}