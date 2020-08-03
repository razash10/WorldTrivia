package com.razash.worldtrivia

import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questions.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private val quCountryOfFlag = Constants.getQuestionsCountryOfFlag()
    private val quCapitalOfFlag = Constants.getQuestionsCapitalOfFlag()
    private val quCountryOfCapital = Constants.getQuestionsCountryOfCapital()
    private val quCapitalOfCountry = Constants.getQuestionsCapitalOfCountry()

    private var quSize = quCountryOfFlag.size + quCapitalOfFlag.size +
            quCountryOfCapital.size + quCapitalOfCountry.size

    private var answer: Question? = null
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        Log.i("Questions Size", "$quSize")

        setQuestion()

        btnOption1.setOnClickListener(this)
        btnOption2.setOnClickListener(this)
        btnOption3.setOnClickListener(this)
        btnOption4.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnOption1 -> processChosenAnswer(btnOption1)
            R.id.btnOption2 -> processChosenAnswer(btnOption2)
            R.id.btnOption3 -> processChosenAnswer(btnOption3)
            R.id.btnOption4 -> processChosenAnswer(btnOption4)
        }
    }

    private fun processChosenAnswer(selectedOption: TextView) {
        selectedOption.typeface = Typeface.DEFAULT_BOLD

        if(answer!!.answer == selectedOption.text.toString()) {
            selectedOption.background = ContextCompat
                .getDrawable(this, R.drawable.correct_option_border_bg)
        }
        else {
            selectedOption.background = ContextCompat
                .getDrawable(this, R.drawable.wrong_option_border_bg)
            markCorrectAnswer()
        }

        countDownTimer?.cancel()

        btnOption1.isClickable = false
        btnOption2.isClickable = false
        btnOption3.isClickable = false
        btnOption4.isClickable = false

        when(answer) {
            in quCountryOfFlag -> quCountryOfFlag.remove(answer!!)
            in quCapitalOfFlag -> quCapitalOfFlag.remove(answer!!)
            in quCountryOfCapital -> quCountryOfCapital.remove(answer!!)
            in quCapitalOfCountry -> quCapitalOfCountry.remove(answer!!)
        }

        quSize--

        Handler().postDelayed({
            setQuestion()
        }, 3000)

    }

    private fun markCorrectAnswer() {
        when(answer!!.answer) {
            btnOption1.text.toString() -> btnOption1.background = ContextCompat
                                    .getDrawable(this, R.drawable.correct_option_border_bg)
            btnOption2.text.toString() -> btnOption2.background = ContextCompat
                .getDrawable(this, R.drawable.correct_option_border_bg)
            btnOption3.text.toString() -> btnOption3.background = ContextCompat
                .getDrawable(this, R.drawable.correct_option_border_bg)
            btnOption4.text.toString() -> btnOption4.background = ContextCompat
                .getDrawable(this, R.drawable.correct_option_border_bg)
        }
    }

    private fun setQuestion() {
        btnOption1.isClickable = true
        btnOption2.isClickable = true
        btnOption3.isClickable = true
        btnOption4.isClickable = true

        btnOption1.background = ContextCompat.getDrawable(this,
            R.drawable.default_option_border_bg)
        btnOption2.background = ContextCompat.getDrawable(this,
            R.drawable.default_option_border_bg)
        btnOption3.background = ContextCompat.getDrawable(this,
            R.drawable.default_option_border_bg)
        btnOption4.background = ContextCompat.getDrawable(this,
            R.drawable.default_option_border_bg)

        btnOption1.typeface = Typeface.DEFAULT
        btnOption2.typeface = Typeface.DEFAULT
        btnOption3.typeface = Typeface.DEFAULT
        btnOption4.typeface = Typeface.DEFAULT

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
        answer = questions[randomIndexList[randAnswer]]
        ivFlag.setImageResource(answer!!.image)

        btnOption1.text = questions[randQuestion1].answer
        btnOption2.text = questions[randQuestion2].answer
        btnOption3.text = questions[randQuestion3].answer
        btnOption4.text = questions[randQuestion4].answer

        if(answer!!.question.isNotEmpty()) {
            val temp = tvQuestion.text.toString()
            val string = "$temp ${answer!!.question}?"
            tvQuestion.text = string
        }

        processTimer()

    }

    private fun processTimer() {
        var i = 0
        countDownTimer = object : CountDownTimer(10000, 1000) {
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