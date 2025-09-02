package com.example.quizapplayout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapplayout.databinding.ActivityMainBinding
import com.example.quizapplayout.ui.QuestionActivity
import com.example.quizapplayout.utils.Constants

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonStart.setOnClickListener {
            if (!binding.name.text.isNullOrEmpty()){
                Intent(this@MainActivity, QuestionActivity::class.java).also {
                    it.putExtra(Constants.USER_NAME,binding.name.text.toString())
                    startActivity(it)
                    finish()
                }
            }else{
                Toast.makeText(this,"no name", Toast.LENGTH_LONG).show()
            }
        }
    }
}