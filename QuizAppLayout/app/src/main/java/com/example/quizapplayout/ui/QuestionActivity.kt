package com.example.quizapplayout.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapplayout.R
import com.example.quizapplayout.databinding.ActivityQuestionBinding
import com.example.quizapplayout.model.Question
import com.example.quizapplayout.utils.Constants

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var progressBar: ProgressBar
    lateinit var textViewProgress: TextView
    lateinit var textViewQuestion: TextView
    lateinit var flagImg: ImageView
    lateinit var textViewOptionOne: TextView
    lateinit var textViewOptionTwo: TextView
    lateinit var textViewOptionThree: TextView
    lateinit var textViewOptionFour: TextView
    lateinit var binding: ActivityQuestionBinding

//    private var selectedOption = 0
    private lateinit var checkButton: Button

    val currentPostion = 1

    private var questionsCounter = 0

    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private lateinit var questionList : MutableList<Question>

    lateinit var name: String
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        progressBar = findViewById(R.id.progress_bar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_view)
        flagImg = findViewById(R.id.image_flag)
        textViewOptionOne = findViewById(R.id.text_view_option_one)
        textViewOptionTwo = findViewById(R.id.text_view_option_two)
        textViewOptionThree = findViewById(R.id.text_view_option_three)
        textViewOptionFour = findViewById(R.id.text_view_option_Four)
        checkButton = findViewById(R.id.button_check)
        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        checkButton = findViewById(R.id.button_check)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        questionList = Constants.getQuestions()
        progressBar.max = questionList.size
        showNextQuestion()
        if (intent.hasExtra(Constants.USER_NAME)){
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }

    private fun showNextQuestion(){

        if (questionsCounter < questionList.size){
            checkButton.text = "CHECK"
            currentQuestion = questionList[questionsCounter]

            resetOption()
            val question = questionList[questionsCounter]
            flagImg.setImageResource(R.mipmap.ic_launcher)
            progressBar.progress = questionsCounter
            textViewProgress.text = "${questionsCounter+1}/${progressBar.max}"
            textViewQuestion.text  = question.question
            textViewOptionOne.text = question.optionOne
            textViewOptionTwo.text = question.optionTwo
            textViewOptionThree.text = question.optionThree
            textViewOptionFour.text = question.optionFour

        }else {
            checkButton.text = "FINISH"
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME,name)
                it.putExtra(Constants.SCORE,score)
                it.putExtra(Constants.TOTAL_QUESTIONS,questionList.size)
                startActivity(it)
            }

        }
        answered = false
        questionsCounter++

    }

    private fun resetOption(){
        val options = mutableListOf<TextView>()
        options.add(textViewOptionOne)
        options.add(textViewOptionTwo)
        options.add(textViewOptionThree)
        options.add(textViewOptionFour)
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOption(textView: TextView,selectedOptionNum: Int){
        resetOption()
        selectedAnswer = selectedOptionNum
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface,Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.text_view_option_one -> {
                selectedOption(textViewOptionOne,1)
            }
            R.id.text_view_option_two -> {
                selectedOption(textViewOptionTwo,2)
            }
            R.id.text_view_option_three -> {
                selectedOption(textViewOptionThree,3)
            }
            R.id.text_view_option_Four -> {
                selectedOption(textViewOptionFour,4)
            }
            R.id.button_check -> {

                if (!answered){
                    checkAnswer()
                } else {
                  showNextQuestion()
                }
                selectedAnswer = 0
            }

        }
    }
    private fun checkAnswer(){

        answered = true

        if (selectedAnswer == currentQuestion.correctAnswer){
            score++
            highlightAnswer(selectedAnswer)
        }else{
            when(selectedAnswer){
                1->{
                    textViewOptionOne.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_boder_bg)
                }
                2->{
                    textViewOptionTwo.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_boder_bg)
                }
                3->{
                    textViewOptionThree.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_boder_bg)
                }
                4->{
                    textViewOptionFour.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_boder_bg)
                }
            }
        }
        checkButton.text = "NEXT"
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer
        highlightAnswer(selectedAnswer)
    }

    private fun highlightAnswer(answer:Int){
        when(answer){
            1->{
                textViewOptionOne.background = ContextCompat.getDrawable(this,R.drawable.correct_option_boder_bg)
            }
            2->{
                textViewOptionTwo.background = ContextCompat.getDrawable(this,R.drawable.correct_option_boder_bg)
            }
            3->{
                textViewOptionThree.background = ContextCompat.getDrawable(this,R.drawable.correct_option_boder_bg)
            }
            4->{
                textViewOptionFour.background = ContextCompat.getDrawable(this,R.drawable.correct_option_boder_bg)
            }
        }
    }

}