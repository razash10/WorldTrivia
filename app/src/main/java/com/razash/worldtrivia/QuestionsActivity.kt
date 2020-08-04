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
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private val quCountryOfFlag = Constants.getQuestionsCountryOfFlag()
    private val quCapitalOfFlag = Constants.getQuestionsCapitalOfFlag()
    private val quCountryOfCapital = Constants.getQuestionsCountryOfCapital()
    private val quCapitalOfCountry = Constants.getQuestionsCapitalOfCountry()

    private var answer: Question? = null
    private var countDownTimer: CountDownTimer? = null

    private var lives = 2
    private var currentScoreOfQuestion: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        setQuestion()

        btnOption1.setOnClickListener(this)
        btnOption2.setOnClickListener(this)
        btnOption3.setOnClickListener(this)
        btnOption4.setOnClickListener(this)

    }

    private fun buttonsAreClickable(option: Boolean) {
        btnOption1.isClickable = option
        btnOption2.isClickable = option
        btnOption3.isClickable = option
        btnOption4.isClickable = option
    }

    override fun onClick(view: View?) {
        countDownTimer?.cancel()

        buttonsAreClickable(false)

        when(view?.id) {
            R.id.btnOption1 -> preProcessChosenAnswer(btnOption1)
            R.id.btnOption2 -> preProcessChosenAnswer(btnOption2)
            R.id.btnOption3 -> preProcessChosenAnswer(btnOption3)
            R.id.btnOption4 -> preProcessChosenAnswer(btnOption4)
        }
    }

    private fun preProcessChosenAnswer(selectedOption: TextView) {
        selectedOption.typeface = Typeface.DEFAULT_BOLD
        selectedOption.background = ContextCompat
            .getDrawable(this, R.drawable.chosen_option_delay)

        Handler().postDelayed({ processChosenAnswer(selectedOption) }, 3000)
    }

    private fun processChosenAnswer(selectedOption: TextView) {
        selectedOption.typeface = Typeface.DEFAULT_BOLD

        if(answer!!.answer == selectedOption.text.toString()) { // if selected correct answer
            selectedOption.background = ContextCompat
                .getDrawable(this, R.drawable.chosen_correct_option)
            var currentScore = tvScore.text.toString().toLong()
            currentScore += currentScoreOfQuestion
            tvScore.text = currentScore.toString()
        }
        else {
            selectedOption.background = ContextCompat
                .getDrawable(this, R.drawable.chosen_wrong_option)
            markCorrectAnswer()
            lives--
        }

        when(answer) {
            in quCountryOfFlag -> quCountryOfFlag.remove(answer!!)
            in quCapitalOfFlag -> quCapitalOfFlag.remove(answer!!)
            in quCountryOfCapital -> quCountryOfCapital.remove(answer!!)
            in quCapitalOfCountry -> quCapitalOfCountry.remove(answer!!)
        }

        Handler().postDelayed({ setQuestion() }, 3000)

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

    private fun resetButtonsViewToDefault() {
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
    }

    private fun validateLives() {
        when(lives) {
            1 -> ivLives.setImageResource(R.drawable.heart)
            0 -> ivLives.setImageResource(0)
            -1 -> {
                Log.i("Exit", "Out of lives")
                exitProcess(0)
            }
        }
    }

    private fun setQuestion() {
        validateLives()

        buttonsAreClickable(true)

        resetButtonsViewToDefault()

        val categoryOptions = ArrayList<Int>()

        if(quCountryOfFlag.size >= 4) categoryOptions.add(0)
        if(quCapitalOfFlag.size >= 4) categoryOptions.add(1)
        if(quCountryOfCapital.size >= 4) categoryOptions.add(2)
        if(quCapitalOfCountry.size >= 4) categoryOptions.add(3)

        if(categoryOptions.isEmpty()) {
            Log.i("Exit", "Out of questions")
            exitProcess(0)
        }

        when (categoryOptions.random()) {
            0 -> {
                tvQuestion.text = "לאיזו מדינה שייך הדגל שבתמונה?"
                processLayout(quCountryOfFlag)
            }
            1 -> {
                tvQuestion.text = "לאיזו עיר בירה שייך הדגל שבתמונה?"
                processLayout(quCapitalOfFlag)
            }
            2 -> {
                tvQuestion.text = "לאיזו מדינה שייכת עיר הבירה"
                processLayout(quCountryOfCapital)
            }
            3 -> {
                tvQuestion.text = "מהי עיר הבירה של"
                processLayout(quCapitalOfCountry)
            }
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

        startTimer()

    }

    private fun startTimer() {
        var i = 0
        countDownTimer = object : CountDownTimer(10000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                Log.v("Timer", "Tick of Progress $i $millisUntilFinished")
                pgTimer.progress = (++i) * 2
                currentScoreOfQuestion = millisUntilFinished
            }

            override fun onFinish() {
                Log.v("Timer", "Time Ran Out")
                pgTimer.progress = 1050
                buttonsAreClickable(false)
                markCorrectAnswer()
                lives--
                Handler().postDelayed({
                    setQuestion()
                }, 3000)
            }
        }.start()

    }



}