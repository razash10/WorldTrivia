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
            in 1..499 -> tvCongrats.text = "לא רע!"
            in 500..999 -> tvCongrats.text = "טוב!"
            in 1000..1999 -> tvCongrats.text = "נהדר!"
            in 2000..3999 -> tvCongrats.text = "מדהים!"
            in 4000..7999 -> tvCongrats.text = "אדיר!"
            in 8000..15999 -> tvCongrats.text = "וואו!"
            in 16000..31999 -> tvCongrats.text = "מטורף!!"
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

        if(score!! > 1000) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("ScoreBoard")
            val post: MutableMap<String, Any> = HashMap()
            post["name"] = tvName.text.toString()
            post["score"] = score
            myRef.push().setValue(post)
        }

    }
}
