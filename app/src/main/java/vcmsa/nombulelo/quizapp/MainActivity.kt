package vcmsa.nombulelo.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat
        //Code goes here
        //link the elements from the GUI to the backend.
        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val btnStart = findViewById<Button>(R.id.btnStart)

        //retrieve the data from the elements
        btnStart.setOnClickListener {
            val username = edtUsername.text.toString()
            Log.d("Username", username)
            //start  if the username is  empty
            if (username.isEmpty()) {
                edtUsername.error = "Please enter your name"
                return@setOnClickListener
            }

            //start the quiz activity
            val intent = Intent(this, QuizActvity::class.java).putExtra("username", username)
            startActivity(intent)
            finish()
        }
    }
}





