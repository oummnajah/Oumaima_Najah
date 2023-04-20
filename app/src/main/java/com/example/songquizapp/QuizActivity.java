package com.example.songquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {


    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    Button next;
    String rightAnswer;
    String Answer;
    List<Question> questions = new ArrayList<>();
    int score;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        next = findViewById(R.id.bNext);
        lblQuestion = findViewById(R.id.Q1p);
        optionA = findViewById(R.id.rb1);
        optionB = findViewById(R.id.rb2);
        score = 0;
        radioGroup = findViewById(R.id.rg);

        databaseReference = FirebaseDatabase.getInstance().getReference("songquizapp").child("question");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot questionSnapshot : snapshot.getChildren()) {
                    Question question = questionSnapshot.getValue(Question.class);
                    questions.add(question);
                }

                loadQuestion();
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // En cas d'erreur de lecture de la base de données
                Log.e("QuestionActivity", "Error reading questions from database", error.toException());
            }
        });
    } private void loadQuestion() {
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));



            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(QuizActivity.this, scor.class);

            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op) {
            case R.id.rb1:
                Answer = "A";
                break;

            case R.id.rb2:
                Answer = "B";
                break;



            default:
                return;

        }

        radioGroup.clearCheck();

        isRightOrWrong(Answer);
        loadQuestion();

    }

    private void isRightOrWrong(String Answer){

        if(Answer.equals(rightAnswer)) {
            this.score += 1;

        }


    }


}


