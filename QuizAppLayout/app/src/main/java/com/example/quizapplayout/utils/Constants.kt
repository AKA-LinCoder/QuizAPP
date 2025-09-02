package com.example.quizapplayout.utils

import com.example.quizapplayout.model.Question
import kotlin.random.Random

object Constants {

    const val USER_NAME = "Mike"
    const val TOTAL_QUESTIONS = "total_questions"
    const val SCORE="correct_answer"



    fun getQuestions(isRandom: Boolean = true): MutableList<Question> {
        // 3. 批量初始化题目列表：减少add()重复调用，代码更简洁
        val questionList = mutableListOf(
            // Question参数说明：id, 问题内容, 分值, 选项1, 选项2, 选项3, 选项4, 正确答案索引（1-4）
            Question(1, "Which planet is known as the Red Planet?", 1, "Venus", "Mars", "Jupiter", "Saturn", 2),
//            Question(2, "What is the capital of France?", 1, "London", "Berlin", "Paris", "Madrid", 3),
//            Question(3, "Which gas do plants absorb from the atmosphere?", 1, "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen", 2),
//            Question(4, "How many continents are there in the world?", 1, "5", "6", "7", "8", 3),
//            Question(5, "Which programming language is known for Android development?", 1, "Swift", "Kotlin", "Python", "C#", 2),
//            Question(6, "What is the largest ocean on Earth?", 1, "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean", 4),
//            Question(7, "Which planet is closest to the Sun?", 1, "Venus", "Mars", "Mercury", "Earth", 3),
//            Question(8, "What is the chemical symbol for water?", 1, "CO2", "H2O", "NaCl", "O2", 2),
//            Question(9, "Which animal is known as the king of the jungle?", 1, "Lion", "Tiger", "Elephant", "Giraffe", 1),
//            Question(10, "What is the square root of 64?", 1, "6", "7", "8", "9", 3)
        )

        // 4. 支持随机排序：满足用户多样化刷题需求，可通过参数关闭
        return if (isRandom) {
            questionList.shuffled(Random).toMutableList()
        } else {
            questionList
        }
    }

}