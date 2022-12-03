package com.applicaiton.randomquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button skipBtn, submitBtn;
    private TextView question;
    private RadioButton a, b, c;

    private TextView totalAnswered;

    private List<String> listOfQuestion = new ArrayList<>();
    private List<String> filteredQuestions = new ArrayList<>();
    private List<String> removeList = new ArrayList<>();

    private TextView totalSkipped;

    private TextView title;

    //Check Duplicates
    HashSet<Integer> duplicates = new HashSet<>();
    int rIndex;

    int count = 0;
    private int skippedCount = 0;

    int loopIndex = 0;
    int showCount = 0;
    int toRunLoop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skipBtn = findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(this);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        totalAnswered = findViewById(R.id.totalAnswered);
        totalSkipped = findViewById(R.id.totalSkipped);

        title = findViewById(R.id.title);

        question = findViewById(R.id.question);
        a = findViewById(R.id.a);
        a.setOnClickListener(this);
        b = findViewById(R.id.b);
        b.setOnClickListener(this);
        c = findViewById(R.id.c);
        c.setOnClickListener(this);

        loadQuestion();
    }
//

    private void loadQuestion() {
        for (int i = 0; i < 50; i++) {
            listOfQuestion.add("Question Number : " + (i + 1));
        }
        Collections.shuffle(listOfQuestion);

        for (int i = 0; i < 10; i++) {
            filteredQuestions.add(listOfQuestion.get(i));
        }
        toRunLoop = filteredQuestions.size();
        title.setText("Selected " + (filteredQuestions.size()) + " Questions From " + listOfQuestion.size() + " Questions");
        showQuestions();
    }

    private void showQuestions() {
        if (showCount < toRunLoop) {
            //int index = randomIndex(filteredQuestions);
            String q = filteredQuestions.get(loopIndex);
            Log.d("QUESTION_CAME", String.valueOf(loopIndex));
            question.setText(q);
            showCount++;
        } else {
            filteredQuestions.clear();
            filteredQuestions.addAll(removeList);
            question.setText(filteredQuestions.get(0));
            Log.d("QUESTION_CAME", filteredQuestions.get(0));
            removeList.clear();
            loopIndex = 1;
            toRunLoop = filteredQuestions.size();
            showCount = 1;
        }
    }

//    private int randomIndex(List<String> list) {
//        Random random = new Random();
//        rIndex = random.nextInt(list.size());
//        if (!duplicates.contains(rIndex)) {
//            duplicates.add(rIndex);
//            return rIndex;
//        } else {
//            return randomIndex(list);
//        }
//    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.skipBtn:
                skippedCount++;
                totalSkipped.setText("Skipped: " + skippedCount);
                String removed = filteredQuestions.get(loopIndex);
                removeList.add(removed);
                loopIndex++;
                showQuestions();
                break;
            case R.id.submitBtn:
                if (count >= 10) {
                    Toast.makeText(this, "End", Toast.LENGTH_SHORT).show();
                    return;
                }
                count++;
                loopIndex++;
                totalAnswered.setText("Answered: " + count);
                Toast.makeText(this, count + "Questions Answered!", Toast.LENGTH_SHORT).show();
                showQuestions();
                break;
            case R.id.a:
                break;
            case R.id.b:
                break;
            case R.id.c:
                break;
        }
    }
}