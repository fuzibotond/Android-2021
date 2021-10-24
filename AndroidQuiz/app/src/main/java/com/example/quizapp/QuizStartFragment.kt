package com.example.quizapp

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
import android.view.animation.Animation
import android.widget.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlin.math.min
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.databinding.ActivityMainBinding

class QuizStartFragment :Fragment( R.layout.quiz_start_fragment){

    private val TAG = "QuizStartFragment"

    lateinit var textView: TextView
    lateinit var btnGetStarted: Button
    lateinit var inputTextArea: EditText

    lateinit var selectButton: Button
    lateinit var imgViewForProfilePic: ImageView
    lateinit var txtViewInfos: TextView
    lateinit var editTxtForStatus: EditText
    lateinit var v: View

    private val sharedViewModel:SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.quiz_start_fragment, container, false)
        initialize()
        return v
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    fun startQuiz() {
        Log.i(TAG, "btn Get started")
//        val message = editTxtForStatus.text.toString() + " " + inputTextArea.text.toString()
//        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
//            putExtra(MainActivity.EXTRA_MESSAGE, message)
//        }
//        startActivity(intent)
    }
    fun initialize(){
        sharedViewModel.name.observe(viewLifecycleOwner,{name->
            inputTextArea.setText(name)
        })
        textView = v.findViewById(R.id.textViewTitle)
        inputTextArea = v.findViewById(R.id.editTextTextPersonName)
        btnGetStarted = v.findViewById(R.id.buttonGetStarted)
        selectButton = v.findViewById(R.id.selectButton)
        selectButton.setOnClickListener {
            Log.i(TAG, "Pick contact btn pushed")

            getContact.launch(0)

        }
        Log.i(TAG, "We are on Create!")
        btnGetStarted.setOnClickListener {
            Log.i(TAG, "Button Clicked!")
//            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            val snackbar = Snackbar.make(
                it,
                "Button clicked! Thanks: ${inputTextArea.text.toString()}!",
                Snackbar.LENGTH_SHORT
            )
            snackbar.show()

            startQuiz()
            sharedViewModel.saveName(inputTextArea.text.toString())

//            communicator.passDataCom( inputTextArea.text.toString())
            findNavController().navigate(R.id.action_quizStartFragment_to_questionFragment)

        }
        imgViewForProfilePic = v.findViewById(R.id.imgViewForProfilePic)
        imgViewForProfilePic.setOnClickListener{
            getImage.launch(0)
        }
        txtViewInfos = v.findViewById(R.id.textViewInformation)
        editTxtForStatus = v.findViewById(R.id.editTxtForStatus)
        val text = "We are on create..."
        Log.i(TAG, text)
        //val duration = Toast.LENGTH_SHORT
        //val toast = Toast.makeText(applicationContext, text, duration)
        //toast.show()
    }

    val getContact = registerForActivityResult(PickContact()) { uri: Uri? ->
        val cols:Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val resolver = requireActivity().contentResolver
        val rs = resolver.query(uri!!,cols, null, null, null)
        if (rs?.moveToFirst()!!){
            inputTextArea.setText(rs.getString(0))
        }
    }

    val getImage = registerForActivityResult(PickImage()) { uri: Uri? ->
        val resolver = requireActivity().contentResolver
        val rs = resolver.query(uri!!,null, null, null, null)
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(resolver, uri)
        imgViewForProfilePic.setImageBitmap(bitmap.cropCircularArea())
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