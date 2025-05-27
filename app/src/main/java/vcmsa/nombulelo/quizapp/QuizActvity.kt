package vcmsa.nombulelo.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_actvity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat
        //Code goes here
        //link the elements from the GUI to the backend.
        val tvQuizQuestion = findViewById<TextView>(R.id.tvQuizQuestion)
        val rbtngQuizAnswers = findViewById<RadioGroup>(R.id.rbtngQuizAnswers)
        val username = intent.getStringExtra("username")
        val btnNext = findViewById<Button>(R.id.btnNext)
        //Arrrays of Questions and Answers
        val historyQuestions = arrayOf(
            "The Great Wall of China was built in a single continuous project",
            "Julius Caesar was the first Roman emperor",
            "The Declaration of Independence was signed in 1776",
            "The Cold War a direct military war between the United States and the Soviet Union",
            "World War I began in 1914",
        )
        val historyAnswers = arrayOf(
            arrayOf("True", "False"),
            arrayOf("True", "False"),
            arrayOf("True", "False"),
            arrayOf("True", "False"),
            arrayOf("True", "False"),
        )
        var userAnswers = arrayOfNulls<String>(5)
        val correctAnswers = arrayOf(
            "False",
            "False",
            "True",
            "False",
            "True",
        )
        var counter = 0
        tvQuizQuestion.text = historyQuestions[counter]
        for (i in 0 until rbtngQuizAnswers.childCount) {
            val radioButton = rbtngQuizAnswers.getChildAt(i) as RadioButton
            radioButton.text = historyAnswers[counter][i]
        }
        btnNext.setOnClickListener {
            if (counter < 5) {
                val selected = rbtngQuizAnswers.checkedRadioButtonId
                if (selected != -1) {
                    val selectedRbtn = findViewById<RadioButton>(selected)
                    userAnswers[counter] = selectedRbtn.text.toString()
                    counter++
                } else {
                    Toast.makeText(this, "Please selected an answer", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener//stop the execution if the answer is no selected
                }
                if (counter < 5) {
                    tvQuizQuestion.text = historyQuestions[counter].toString()
                    for (i in 0 until rbtngQuizAnswers.childCount) {
                        val radioButton = rbtngQuizAnswers.getChildAt(i) as RadioButton
                        radioButton.text = historyAnswers[counter][i]
                    }
                    rbtngQuizAnswers.clearCheck()

                } else {
                    var intent = Intent(this, ResultyActivity::class.java)
                    var score = 0
                    //calculate score
                    for (i in userAnswers.indices) {
                        if (userAnswers[i] != correctAnswers[i]) {
                            score++
                        }
                    }
                    intent.putExtra("score", score)
                    intent.putExtra("username", username)
                    val incorrectQuestions = ArrayList<String>()
                    for (i in userAnswers.indices) {
                        if (userAnswers[i] != correctAnswers[i]) {
                            incorrectQuestions.add("Q${i + 1}: ${historyQuestions[i]}\nCorrect answer: ${correctAnswers[i]}\n")
                        }
                    }
                    intent.putStringArrayListExtra("incorrectQuestions", incorrectQuestions)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }//end of onCreate
}//end of QuizActvity

