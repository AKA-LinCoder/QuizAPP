package com.example.quizapplayout.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapplayout.MainActivity
import com.example.quizapplayout.R
import com.example.quizapplayout.databinding.ActivityResultBinding
import com.example.quizapplayout.utils.Constants

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val totalQuestions =  intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        var score =  intent.getIntExtra(Constants.SCORE,0)
        if (intent.hasExtra(Constants.USER_NAME)){
            binding.tvName.text = intent.getStringExtra(Constants.USER_NAME)
        }
        if (intent.hasExtra(Constants.SCORE)){
            binding.tvScore.text = "Your score is $score out of $totalQuestions"
        }
        binding.btnFinish.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}