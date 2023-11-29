package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityQuizBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var list:ArrayList<QuestionModel>
    private  var count:Int=0
    private var score:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list=ArrayList<QuestionModel>()
        //get data in firebase
        Firebase.firestore.collection("quiz")
            .get().addOnSuccessListener {
                doc->
                list.clear()
                for (i in doc.documents){
                    //create object in QuestionModel class
                    var questionModel=i.toObject(QuestionModel::class.java)
                    list.add(questionModel!!)
                }
                binding.question.setText(list.get(count).question)
                binding.option1.setText(list.get(count).option1)
                binding.option2.setText(list.get(count).option2)
                binding.option3.setText(list.get(count).option3)
                binding.option4.setText(list.get(count).option4)
            }

//        list.add(QuestionModel("Who is the PM of Pakistan","Hassan Muawia","Nawaz Sharif","Zardari","Imran Khan","Hassan Muawia"))
//        list.add(QuestionModel("Who is the PM of Pakistan","Imran Khan","Nawaz Sharif","Zardari","Hassan Muawia","Hassan Muawia"))
//        list.add(QuestionModel("Who is the PM of Pakistan","Hassan Muawia","Nawaz Sharif","Zardari","Imran Khan","Hassan Muawia"))
//        list.add(QuestionModel("Who is the PM of Pakistan","Imran Khan","Nawaz Sharif","Zardari","Hassan Muawia","Hassan Muawia"))


        binding.option1.setOnClickListener {

            nextData(binding.option1.text.toString())

        }
        binding.option2.setOnClickListener {
            nextData(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener {
            nextData(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener {
            nextData(binding.option4.text.toString())
        }
    }

    private fun nextData(i: String) {
        //agr count ka size list se kaam h to update karta rhy
        if (count<=list.size) {
            //count ko get karo or dekho k i k equals h i oper button k kon se button par click hoya h
            if (list.get(count).ans.equals(i)) {
                score++
            }
        }
        count++
        //agr count ka size list se zada ho gya h to to score show kar de
        if (count>=list.size){
            //score show
            val intent= Intent(this,ScoreActivity::class.java)
            intent.putExtra("SCORE",score)
            startActivity(intent)
            finish()
        }else{
            binding.question.setText(list.get(count).question)
            binding.option1.setText(list.get(count).option1)
            binding.option2.setText(list.get(count).option2)
            binding.option3.setText(list.get(count).option3)
            binding.option4.setText(list.get(count).option4)
        }




    }
}