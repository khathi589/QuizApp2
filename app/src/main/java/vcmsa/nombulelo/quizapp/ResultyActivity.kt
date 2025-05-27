package vcmsa.nombulelo.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultyActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resulty)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Tvscore = findViewById<TextView>(R.id.tvResultTitle)
        val btnFinish = findViewById<Button>(R.id.btnFinish)
        val incorrectQuestions = intent.getStringArrayListExtra("incorrectQuestions")
        val incorrectQuestionsTextView = findViewById<TextView>(R.id.textView2)
        if (!incorrectQuestions.isNullOrEmpty()) {
            incorrectQuestionsTextView.text = incorrectQuestions.joinToString("\n\n")
        } else {
            incorrectQuestionsTextView.text = "No incorrect questions."
        }

        // Logic
        //Get the score from the intent
        val score = intent.getIntExtra("score", 0)
        val username = intent.getStringExtra("username")
        Tvscore.text = "$username, your score is $score out of 5"
        if (score < 3) {
            Tvscore.text = "Well done! You answered all questions correctly!"
        } else {
            Tvscore.text = "Congratulations!,Your score is $score"
        }
        btnFinish.setOnClickListener {
            finish()
        }
    }
}//code ends here





