package com.example.hendzee.funplus;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewQuestion, textViewScore, textViewTimer;
    ImageView imageView0, imageView1, imageView2, imageView3;
    ImageView bananaTimer1, bananaTimer2, bananaTimer3, bananaTimer4;
    int selectedPosition;
    int score = 0;
    int numQuestion = 0;
    ArrayList<Integer>answerList;

    public void selectAnswer(View view){
        if (selectedPosition == Integer.parseInt((String) view.getTag())){
            score++;
        }

        numQuestion++;
        textViewScore.setText(Integer.toString(score) + "/" + Integer.toString(numQuestion));
        answerList.clear();
        generateQuestion();
    }

    public void setImageButton(ImageView image, int numOfImage){
        switch(numOfImage){
            case 1:
                image.setImageResource(R.drawable.butter);
                break;
            case 2:
                image.setImageResource(R.drawable.candy);
                break;
            case 3:
                image.setImageResource(R.drawable.cereals);
                break;
            case 4:
                image.setImageResource(R.drawable.chocolate);
                break;
            case 5:
                image.setImageResource(R.drawable.cupcake);
                break;
            case 6:
                image.setImageResource(R.drawable.doughnut);
                break;
            case 7:
                image.setImageResource(R.drawable.fries);
                break;
            case 8:
                image.setImageResource(R.drawable.gingerbread);
                break;
            case 9:
                image.setImageResource(R.drawable.grapes);
                break;
            case 10:
                image.setImageResource(R.drawable.hamburguer);
                break;
            case 11:
                image.setImageResource(R.drawable.ice_cream);
                break;
            case 12:
                image.setImageResource(R.drawable.jam);
                break;
            case 13:
                image.setImageResource(R.drawable.lemon);
                break;
            case 14:
                image.setImageResource(R.drawable.meat);
                break;
            case 15:
                image.setImageResource(R.drawable.pineapple);
                break;
            case 16:
                image.setImageResource(R.drawable.raspberry);
                break;
            case 17:
                image.setImageResource(R.drawable.sandwich);
                break;
            case 18:
                image.setImageResource(R.drawable.sushi);
                break;
            case 19:
                image.setImageResource(R.drawable.taco);
                break;
            case 20:
                image.setImageResource(R.drawable.toffee);
                break;
            default:
                image.setImageResource(R.drawable.butter);
                break;
        }
    }

    public void generateQuestion(){
        answerList = new ArrayList<Integer>();

        Random rand = new Random();

        int firstNumber = rand.nextInt(9) + 1;
        int secondNumber = rand.nextInt(9) + 1;
        int incorectAnswer;

        int answer = firstNumber + secondNumber;
        String strQuestion = Integer.toString(firstNumber) + " + " + Integer.toString(secondNumber)
                + " = ";

        textViewQuestion.setText(strQuestion);

        selectedPosition = rand.nextInt(4);

        incorectAnswer = rand.nextInt(19) + 1;

        for (int x=0; x<4; x++){
            if (x == selectedPosition){
                answerList.add(answer);
            }else{
                do{
                    incorectAnswer = rand.nextInt(19) + 1;
                }while(incorectAnswer == answer);

                answerList.add(incorectAnswer);
            }
        }

        setImageButton(imageView0, (int) answerList.get(0));
        setImageButton(imageView1, (int) answerList.get(1));
        setImageButton(imageView2, (int) answerList.get(2));
        setImageButton(imageView3, (int) answerList.get(3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout mainContain = (RelativeLayout) findViewById(R.id.mainContain);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        imageView0 = (ImageView) findViewById(R.id.imageView0);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        bananaTimer1 = (ImageView) findViewById(R.id.bananaTimer1);
        bananaTimer2 = (ImageView) findViewById(R.id.bananaTimer2);
        bananaTimer3 = (ImageView) findViewById(R.id.bananaTimer3);
        bananaTimer4 = (ImageView) findViewById(R.id.bananaTimer4);


        generateQuestion();

        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished / 1000 == 15){
                    bananaTimer4.setVisibility(View.INVISIBLE);
                }else if (millisUntilFinished / 1000 == 10) {
                    bananaTimer3.setVisibility(View.INVISIBLE);
                }else if (millisUntilFinished / 1000 == 5){
                    bananaTimer2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFinish() {
                mainContain.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
}
