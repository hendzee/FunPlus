package com.example.hendzee.funplus;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<DataItem> dataItems;
    TextView textViewQuestion, textViewScore, textViewTimer, textViewScoreResult, textViewRemember;
    ImageView imageView0, imageView1, imageView2, imageView3;
    ImageView bananaTimer1, bananaTimer2, bananaTimer3, bananaTimer4;
    RelativeLayout startMenu, mainContain, valueBoard;
    LinearLayout scoreBoard;
    int selectedPosition;
    int score = 0;
    int numQuestion = 0;
    ArrayList<Integer>answerList;


    public void prepareGame(View view){
        valueBoard.setVisibility(View.VISIBLE);
        startMenu.setVisibility(View.INVISIBLE);
        scoreBoard.setVisibility(View.INVISIBLE);

        new CountDownTimer(15000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewRemember.setText("Remember each value ! " + "(" +
                        String.valueOf(millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                startGame();
            }
        }.start();
    }

    public void startGame(){
        score = 0;
        numQuestion = 0;
        startMenu.setVisibility(View.INVISIBLE);
        scoreBoard.setVisibility(View.INVISIBLE);
        mainContain.setVisibility(View.VISIBLE);
        textViewScore.setText("0");
        bananaTimer2.setVisibility(View.VISIBLE);
        bananaTimer3.setVisibility(View.VISIBLE);
        bananaTimer4.setVisibility(View.VISIBLE);
        valueBoard.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished / 1000 == 45){
                    bananaTimer4.setVisibility(View.INVISIBLE);
                }else if (millisUntilFinished / 1000 == 30) {
                    bananaTimer3.setVisibility(View.INVISIBLE);
                }else if (millisUntilFinished / 1000 == 15){
                    bananaTimer2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFinish() {
                mainContain.setVisibility(View.INVISIBLE);
                textViewScoreResult.setText(Integer.toString(score) + "/" + Integer.toString(numQuestion));
                scoreBoard.setVisibility(View.VISIBLE);
            }
        }.start();
    }

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

    public void generateListValue(){
        dataItems = new ArrayList<DataItem>();
        dataItems.add(new DataItem(R.drawable.butter, "1"));
        dataItems.add(new DataItem(R.drawable.candy, "2"));
        dataItems.add(new DataItem(R.drawable.cereals, "3"));
        dataItems.add(new DataItem(R.drawable.chocolate, "4"));
        dataItems.add(new DataItem(R.drawable.cupcake, "5"));
        dataItems.add(new DataItem(R.drawable.doughnut, "6"));
        dataItems.add(new DataItem(R.drawable.fries, "7"));
        dataItems.add(new DataItem(R.drawable.gingerbread, "8"));
        dataItems.add(new DataItem(R.drawable.grapes, "9"));
        dataItems.add(new DataItem(R.drawable.hamburguer, "10"));
        dataItems.add(new DataItem(R.drawable.ice_cream, "11"));
        dataItems.add(new DataItem(R.drawable.jam, "12"));
        dataItems.add(new DataItem(R.drawable.lemon, "13"));
        dataItems.add(new DataItem(R.drawable.meat, "14"));
        dataItems.add(new DataItem(R.drawable.pineapple, "15"));
        dataItems.add(new DataItem(R.drawable.raspberry, "16"));
        dataItems.add(new DataItem(R.drawable.sandwich, "17"));
        dataItems.add(new DataItem(R.drawable.sushi, "18"));
        dataItems.add(new DataItem(R.drawable.taco, "19"));
        dataItems.add(new DataItem(R.drawable.toffee, "20"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewValue = (ListView) findViewById(R.id.listViewValue);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewScoreResult = (TextView) findViewById(R.id.textViewScoreResult);
        textViewRemember = (TextView) findViewById(R.id.textViewRemember);
        imageView0 = (ImageView) findViewById(R.id.imageView0);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        bananaTimer1 = (ImageView) findViewById(R.id.bananaTimer1);
        bananaTimer2 = (ImageView) findViewById(R.id.bananaTimer2);
        bananaTimer3 = (ImageView) findViewById(R.id.bananaTimer3);
        bananaTimer4 = (ImageView) findViewById(R.id.bananaTimer4);
        startMenu = (RelativeLayout) findViewById(R.id.startMenu);
        mainContain = (RelativeLayout) findViewById(R.id.mainContain);
        scoreBoard = (LinearLayout) findViewById(R.id.scoreBoard);
        valueBoard = (RelativeLayout) findViewById(R.id.valueBoard);

        generateListValue();

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.itemrow, dataItems);
        listViewValue.setAdapter(customAdapter);
    }
}
