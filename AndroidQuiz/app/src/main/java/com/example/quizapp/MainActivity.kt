package com.example.quizapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.material.snackbar.Snackbar
import android.provider.MediaStore
import android.widget.*
import android.graphics.Bitmap
import kotlin.math.min


class MainActivity : AppCompatActivity() {
    val TEXT_VIEW_KEY = "state1"
    val GAME_STATE_KEY = "state2"
    private val TAG = "MyActivity"
    companion object{
        var EXTRA_MESSAGE = ""
    }

    lateinit var textView: TextView
    lateinit var btnGetStarted:Button
    lateinit var inputTextArea:EditText
    var gameState: String? = null
    lateinit var selectButton:Button
    lateinit var imgViewForProfilePic:ImageView
    lateinit var txtViewInfos:TextView
    lateinit var editTxtForStatus:EditText
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.text = savedInstanceState?.getString(TEXT_VIEW_KEY)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "We are on resume!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textViewTitle)
        gameState = savedInstanceState?.getString(GAME_STATE_KEY)
        inputTextArea = findViewById(R.id.editTextTextPersonName)
        btnGetStarted = findViewById(R.id.buttonGetStarted)
        selectButton = findViewById(R.id.selectButton)
        selectButton.setOnClickListener {
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
            sendMessage()


        }
        imgViewForProfilePic = findViewById(R.id.iVForProfilePic)
        imgViewForProfilePic.setOnClickListener{
            getImage.launch(0)
        }
        txtViewInfos = findViewById(R.id.textViewInformation)
        editTxtForStatus = findViewById(R.id.editTxtForStatus)
        val text = "We are on create..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    fun sendMessage() {
        val message = editTxtForStatus.text.toString() + " " + inputTextArea.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    val getContact = registerForActivityResult(PickContact()) { uri: Uri? ->
        val cols:Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val rs = contentResolver.query(uri!!,cols, null, null, null)
        if (rs?.moveToFirst()!!){
            inputTextArea.setText(rs.getString(0))
        }
    }

    val getImage = registerForActivityResult(PickImage()) { uri: Uri? ->
        val rs = contentResolver.query(uri!!,null, null, null, null)
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        imgViewForProfilePic.setImageBitmap(bitmap.cropCircularArea())
    }

    fun Bitmap.cropCircularArea():Bitmap? {
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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.run {
            putString(GAME_STATE_KEY, gameState)
            putString(TEXT_VIEW_KEY, textView.text.toString())
        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
        val text = "We are on save instance state..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun onStart() {
        super.onStart()
        val text = "We are on start..."
        Log.i(TAG, text)
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
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

    class PickContact : ActivityResultContract<Int, Uri?>() {
        override fun createIntent(context: Context, ringtoneType: Int) =
            Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)

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