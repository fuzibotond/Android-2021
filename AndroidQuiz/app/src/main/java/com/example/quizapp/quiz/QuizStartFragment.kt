package com.example.quizapp.quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlin.math.min
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.HeaderNavigationDrawerBinding
import com.example.quizapp.databinding.QuizStartFragmentBinding
import com.example.quizapp.models.SharedViewModel

class QuizStartFragment :Fragment(R.layout.quiz_start_fragment){

    private val TAG = "QuizStartFragment"

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: QuizStartFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = QuizStartFragmentBinding.inflate(inflater, container, false)
        initialize()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun initialize(){
        binding.editTextTextPersonName.setHint(sharedViewModel.getName())
        binding.selectButton.setOnClickListener {
            getContact.launch(0)
        }
        binding.btnGetStarted.setOnClickListener {

            if(binding.editTextTextPersonName.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Oops! You can't start without name! Please fill it!", Toast.LENGTH_SHORT).show()
            }else{


                    sharedViewModel.saveName(binding.editTextTextPersonName.text.toString())
                    Toast.makeText(requireContext(), "Let's get it started ${sharedViewModel.name.value}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_quizStartFragment_to_questionFragment)
            }
        }
        binding.imgViewForProfilePic.setOnClickListener {
            getImage.launch(0)
        }
    }

    val getContact = registerForActivityResult(PickContact()) { uri: Uri? ->
        val cols:Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val resolver = requireActivity().contentResolver
        val rs = resolver.query(uri!!,cols, null, null, null)
        if (rs?.moveToFirst()!!){
            binding.editTextTextPersonName.setText(rs.getString(0))
        }
    }

    val getImage = registerForActivityResult(PickImage()) { uri: Uri? ->
        val resolver = requireActivity().contentResolver
        val rs = resolver.query(uri!!,null, null, null, null)
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(resolver, uri)
        sharedViewModel.saveProfilePic(bitmap.cropCircularArea())
        binding.imgViewForProfilePic.setImageBitmap(sharedViewModel.imgProfilePic.value)

    }

    fun Bitmap.cropCircularArea(): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)

        val path = Path()
        val radius = min(width / 2f, height / 2f)
        path.apply {
            addCircle(
                width / 2f,
                height / 2f,
                radius,
                Path.Direction.CCW
            )
        }

        canvas.clipPath(path)
        canvas.drawBitmap(this,0f,0f,null)

        val diameter = (radius*2).toInt()
        val x = (width - diameter)/2
        val y = (height - diameter)/2

        return Bitmap.createBitmap(
            bitmap,
            x,
            y,
            diameter,
            diameter)
    }
//    cropCricularArea function source: https://android--code.blogspot.com/2020/06/android-kotlin-bitmap-crop-circular-area.html



    override fun onStart() {
        super.onStart()
        val text = "We are on start..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
       // val toast = Toast.makeText(applicationContext, text, duration)
       // toast.show()
    }

    override fun onPause() {
        super.onPause()
        val text = "We are on pause..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
       // val toast = Toast.makeText(applicationContext, text, duration)
       // toast.show()
    }

    override fun onStop() {
        super.onStop()
        val text = "We are on stop..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
       // val toast = Toast.makeText(applicationContext, text, duration)
       // toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        val text = "We are on destroy..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
       // val toast = Toast.makeText(applicationContext, text, duration)
       // toast.show()
    }



    class PickContact : ActivityResultContract<Int, Uri?>() {
        override fun createIntent(context: Context, ringtoneType: Int) =
            Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)

        override fun parseResult(resultCode: Int, result: Intent?) : Uri? {
            if (resultCode != Activity.RESULT_OK) {
                return null
            }
            return result?.data
        }

    }
    class PickImage: ActivityResultContract<Int, Uri?>(){
        override fun createIntent(context: Context, input: Int?): Intent {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            return intent
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            if (resultCode != Activity.RESULT_OK) {
                return null
            }
            return intent?.data
        }
    }
}