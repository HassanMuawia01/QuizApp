package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            Firebase.auth.createUserWithEmailAndPassword(binding.emailText.editText?.text.toString(),
                binding.passText.editText?.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent=Intent(this,QuizActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this,"Create user",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Fail Create user",Toast.LENGTH_LONG).show()
                    }
            }


        }
    }
}