package com.razash.worldtrivia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        val score = intent.getStringExtra(Constants.FINAL_SCORE)?.toInt()
        val scoreText = "השגת $score נקודות"
        tvScore.text = scoreText

        if(score == 0) {
            ivTrophy.setImageResource(R.drawable.sad_pepe)
            tvCongrats.text = "אוי לא"
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

        btnLeaderboard.setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            intent.putExtra(Constants.USER_NAME, tvName.text.toString())
            startActivity(intent)
            finish()
        }

        if(score!! > 0) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("ScoreBoard")
            val post: MutableMap<String, Any> = HashMap()
            post["name"] = tvName.text.toString()
            post["score"] = score
            myRef.push().setValue(post)
        }

    }
}
