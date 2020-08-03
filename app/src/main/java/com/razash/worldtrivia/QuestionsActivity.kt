package com.razash.worldtrivia

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_questions.*


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private val quCountryOfFlag = Constants.getQuestionsCountryOfFlag()
    private val quCapitalOfFlag = Constants.getQuestionsCapitalOfFlag()
    private val quCountryOfCapital = Constants.getQuestionsCountryOfCapital()
    private val quCapitalOfCountry = Constants.getQuestionsCapitalOfCountry()

    private val quSize = quCountryOfFlag.size + quCapitalOfFlag.size +
            quCountryOfCapital.size + quCapitalOfCountry.size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        Log.i("Questions Size", "$quSize")

        chooseCategory()

        processTimer()

    }

    override fun onClick(p0: View?) {

    }

    private fun chooseCategory() {
        val randCategory = (0..3).random()

        if(randCategory == 0) {
            tvQuestion.text = "לאיזו מדינה שייך הדגל שבתמונה?"
            processLayout(quCountryOfFlag)
        }

        if(randCategory == 1) {
            tvQuestion.text = "לאיזו עיר בירה שייך הדגל שבתמונה?"
            processLayout(quCountryOfFlag)
        }

        if(randCategory == 2) {
            tvQuestion.text = "לאיזו מדינה שייכת עיר הבירה"
            processLayout(quCountryOfCapital)
        }

        if(randCategory == 3) {
            tvQuestion.text = "מהי עיר הבירה של"
            processLayout(quCapitalOfCountry)
        }
    }

    private fun processLayout(questions: ArrayList<Question>) {
        val randomIndexList = (0 until questions.size).shuffled().take(4)
        val randQuestion1 = randomIndexList[0]
        val randQuestion2 = randomIndexList[1]
        val randQuestion3 = randomIndexList[2]
        val randQuestion4 = randomIndexList[3]
        val randAnswer = (0..3).random()
        val answer = questions[randomIndexList[randAnswer]]
        ivFlag.setImageResource(answer.image)

        btnOption1.text = questions[randQuestion1].answer
        btnOption2.text = questions[randQuestion2].answer
        btnOption3.text = questions[randQuestion3].answer
        btnOption4.text = questions[randQuestion4].answer

        if(answer.question.isNotEmpty()) {
            val temp = tvQuestion.text.toString()
            val string = "$temp ${answer.question}?"
            tvQuestion.text = string
        }

        questions.remove(answer)
    }

    private fun processTimer() {
        var i = 0
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.v("Timer", "Tick of Progress$i$millisUntilFinished")
                progressBar.progress = (++i) * 10
            }

            override fun onFinish() {
                //Do what you want
                Log.v("Timer", "Time Ran Out")
                progressBar.progress = 100
            }
        }.start()
    }
}