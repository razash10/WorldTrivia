package com.razash.worldtrivia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            if(enteredName.text.toString().isEmpty()) {
                Toast.makeText(this, "הכנס את שמך בשביל להתחיל לשחק",
                    Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, enteredName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }

}