package com.razash.worldtrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        val scoreText = "השגת " + intent.getStringExtra(Constants.FINAL_SCORE) + " נקודות"
        tvScore.text = scoreText

        val score = intent.getStringExtra(Constants.FINAL_SCORE)?.toInt()
        if(score == 0) {
            ivTrophy.setImageResource(0)
            tvCongrats.text = "אופסי..."
        }
        else when(score) {
            in 1..299 -> tvCongrats.text = "טוב!"
            in 300..599 -> tvCongrats.text = "נהדר!"
            in 600..1199 -> tvCongrats.text = "מדהים!"
            in 1200..1999 -> tvCongrats.text = "אדיר!"
            in 2000..2999 -> tvCongrats.text = "וואו!"
            in 3000..4999 -> tvCongrats.text = "פנטסטי!"
            in 5000..9999 -> tvCongrats.text = "מטורף!"
            else -> tvCongrats.text = "אלוף העולם!!!"
        }

        btnReplay.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, tvName.text.toString())
            startActivity(intent)
            finish()
        }

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}