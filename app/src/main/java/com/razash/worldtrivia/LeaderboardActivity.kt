package com.razash.worldtrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.activity_leaderboard.btnFinish
import kotlinx.android.synthetic.main.activity_leaderboard.btnReplay

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val name = intent.getStringExtra(Constants.USER_NAME)

        val results = ArrayList<TextView>()
        results.add(tvName1)
        results.add(tvScore1)
        results.add(tvName2)
        results.add(tvScore2)
        results.add(tvName3)
        results.add(tvScore3)
        results.add(tvName4)
        results.add(tvScore4)
        results.add(tvName5)
        results.add(tvScore5)
        results.add(tvName6)
        results.add(tvScore6)
        results.add(tvName7)
        results.add(tvScore7)
        results.add(tvName8)
        results.add(tvScore8)
        results.add(tvName9)
        results.add(tvScore9)
        results.add(tvName10)
        results.add(tvScore10)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("ScoreBoard")

        val queryRef: Query = myRef.orderByChild("score").limitToLast(10)
        queryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var i=19
                for (postSnapshot in dataSnapshot.children) {
                    val result = postSnapshot.value
                    val list: List<String> = result.toString()
                        .subSequence(1, result.toString().length-1).split(",")
                    val score1 = list[0].split("=")[1]
                    val name1 = list[1].split("=")[1]
                    Log.d("BANANA", "Score = $score1, Name = $name1")

                    results[i].text = score1
                    i--
                    results[i].text = name1
                    i--

                }

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("The read failed" , "${databaseError.code}")
            }
        })

        btnReplay.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, name.toString())
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