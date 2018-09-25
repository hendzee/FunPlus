package com.example.hendzee.funplus;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    ArrayList<DataItem> dataItems;
    TextView textViewQuestion, textViewScore, textViewTimer, textViewScoreResult, questionTotalResult,
            textViewRemember, textViewRank;
    ImageView imageView0, imageView1, imageView2, imageView3, buttonStart;
    RelativeLayout chose_one, chose_two, chose_three, chos_four;
    RelativeLayout startMenu, mainContain, valueBoard;
    RelativeLayout scoreBoard;
    TextView textFood1, textFood2, textFood3, textFood4;
    int selectedPosition;
    int score = 0;
    int numQuestion = 0;
    ArrayList<Integer>answerList;
    MediaPlayer mediaPlayer, mediaPlayer2;
    ArrayList<String> nameOfFood = new ArrayList<String>(asList("Candy", "Burger",
            "Gingerbread", "Doughnut", "Satay", "Ice Cube", "Lemon", "Apple",
            "Grapes", "Jam", "Egg", "Sausage", "Spoon", "Fork", "Gum", "Chocolate",
            "Pinnaple", "Candle", "Sauce", "Cola"));



    public void prepareGame(View view){
        mediaPlayer.start();
        valueBoard.setVisibility(View.VISIBLE);
        startMenu.setVisibility(View.INVISIBLE);
        scoreBoard.setVisibility(View.INVISIBLE);

        new CountDownTimer(15000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewRemember.setText(String.valueOf(millisUntilFinished / 1000));
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
        textViewTimer.setVisibility(View.VISIBLE);
        valueBoard.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               textViewTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mediaPlayer2.start();
                mainContain.setVisibility(View.INVISIBLE);
                textViewScoreResult.setText(Integer.toString(score));
                questionTotalResult.setText(Integer.toString(numQuestion));
                textViewRank.setText(getRank());
                scoreBoard.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void selectAnswer(View view){
        if (selectedPosition == Integer.parseInt((String) view.getTag())){
            score++;
        }

        numQuestion++;

        textViewScore.setText(Integer.toString(score));
        mediaPlayer.start();
        answerList.clear();
        generateQuestion();
    }

    public void setImageButton(ImageView image, TextView textView, int numOfImage){

        switch(numOfImage){
            case 1:
                image.setImageResource(R.drawable.candy);
                textView.setText(nameOfFood.get(0));
                break;
            case 2:
                image.setImageResource(R.drawable.burger);
                textView.setText(nameOfFood.get(1));
                break;
            case 3:
                image.setImageResource(R.drawable.gingerbread);
                textView.setText(nameOfFood.get(2));
                break;
            case 4:
                image.setImageResource(R.drawable.doughnut);
                textView.setText(nameOfFood.get(3));
                break;
            case 5:
                image.setImageResource(R.drawable.satay);
                textView.setText(nameOfFood.get(4));
                break;
            case 6:
                image.setImageResource(R.drawable.ice_cube);
                textView.setText(nameOfFood.get(5));
                break;
            case 7:
                image.setImageResource(R.drawable.lemon);
                textView.setText(nameOfFood.get(6));
                break;
            case 8:
                image.setImageResource(R.drawable.apple);
                textView.setText(nameOfFood.get(7));
                break;
            case 9:
                image.setImageResource(R.drawable.grapes);
                textView.setText(nameOfFood.get(8));
                break;
            case 10:
                image.setImageResource(R.drawable.selai);
                textView.setText(nameOfFood.get(9));
                break;
            case 11:
                image.setImageResource(R.drawable.egg);
                textView.setText(nameOfFood.get(10));
                break;
            case 12:
                image.setImageResource(R.drawable.sosis);
                textView.setText(nameOfFood.get(11));
                break;
            case 13:
                image.setImageResource(R.drawable.spoon);
                textView.setText(nameOfFood.get(12));
                break;
            case 14:
                image.setImageResource(R.drawable.garpu);
                textView.setText(nameOfFood.get(13));
                break;
            case 15:
                image.setImageResource(R.drawable.gum);
                textView.setText(nameOfFood.get(14));
                break;
            case 16:
                image.setImageResource(R.drawable.chocholate);
                textView.setText(nameOfFood.get(15));
                break;
            case 17:
                image.setImageResource(R.drawable.pinaple);
                textView.setText(nameOfFood.get(16));
                break;
            case 18:
                image.setImageResource(R.drawable.candle);
                textView.setText(nameOfFood.get(17));
                break;
            case 19:
                image.setImageResource(R.drawable.saos);
                textView.setText(nameOfFood.get(18));
                break;
            case 20:
                image.setImageResource(R.drawable.cola);
                textView.setText(nameOfFood.get(19));
                break;
            default:
                image.setImageResource(R.drawable.apple);
                textView.setText("Butter");
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
        String strQuestion = Integer.toString(firstNumber) + " + " + Integer.toString(secondNumber);

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

        setImageButton(imageView0, textFood1, (int) answerList.get(0));
        setImageButton(imageView1, textFood2, (int) answerList.get(1));
        setImageButton(imageView2, textFood3, (int) answerList.get(2));
        setImageButton(imageView3, textFood4, (int) answerList.get(3));
    }

    public void generateListValue(){
        dataItems = new ArrayList<DataItem>();
        dataItems.add(new DataItem(R.drawable.candy, nameOfFood.get(0), "1"));
        dataItems.add(new DataItem(R.drawable.burger, nameOfFood.get(1), "2"));
        dataItems.add(new DataItem(R.drawable.gingerbread, nameOfFood.get(2), "3"));
        dataItems.add(new DataItem(R.drawable.doughnut, nameOfFood.get(3), "4"));
        dataItems.add(new DataItem(R.drawable.satay, nameOfFood.get(4), "5"));
        dataItems.add(new DataItem(R.drawable.ice_cube, nameOfFood.get(5), "6"));
        dataItems.add(new DataItem(R.drawable.lemon, nameOfFood.get(6), "7"));
        dataItems.add(new DataItem(R.drawable.apple, nameOfFood.get(7), "8"));
        dataItems.add(new DataItem(R.drawable.grapes, nameOfFood.get(8),"9"));
        dataItems.add(new DataItem(R.drawable.selai, nameOfFood.get(9),"10"));
        dataItems.add(new DataItem(R.drawable.egg, nameOfFood.get(10),"11"));
        dataItems.add(new DataItem(R.drawable.sosis, nameOfFood.get(11),"12"));
        dataItems.add(new DataItem(R.drawable.spoon, nameOfFood.get(12),"13"));
        dataItems.add(new DataItem(R.drawable.garpu, nameOfFood.get(13),"14"));
        dataItems.add(new DataItem(R.drawable.gum, nameOfFood.get(14),"15"));
        dataItems.add(new DataItem(R.drawable.chocholate, nameOfFood.get(15),"16"));
        dataItems.add(new DataItem(R.drawable.pinaple, nameOfFood.get(16),"17"));
        dataItems.add(new DataItem(R.drawable.candle, nameOfFood.get(17),"18"));
        dataItems.add(new DataItem(R.drawable.saos, nameOfFood.get(18),"19"));
        dataItems.add(new DataItem(R.drawable.cola, nameOfFood.get(19),"20"));
    }

    public String getRank(){
        String rank = "";
        int persentage;

        if (numQuestion <= 0){
            persentage = 0;
        }else{
            persentage = (int) (score / numQuestion * 100);
        }

        if (numQuestion > 0 && numQuestion <= 10){
            if (persentage <= 25) {
                rank = "D";
            }else if (persentage > 25 && persentage <= 50) {
                rank = "C";
            }else if (persentage > 50 && persentage <= 100) {
                rank = "B";
            }
        } else if (numQuestion > 10 && numQuestion <= 30){
            if (persentage <= 5){
                rank = "D";
            }else if (persentage > 5 && persentage <= 25) {
                rank = "C";
            }else if (persentage > 25 && persentage <= 50){
                rank = "B";
            }else if (persentage > 50 && persentage <= 100){
                rank = "A";
            }
        }else if (numQuestion > 30 ) {
            if (persentage <= 3){
                rank = "D";
            }else if (persentage > 3 && persentage <= 50){
                rank = "C";
            }else if (persentage > 50 && persentage <= 80){
                rank = "A";
            }else if (persentage > 80 && persentage <= 100){
                rank = "S";
            }
        }else {
                rank = "E";
        }

        return rank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewValue = (ListView) findViewById(R.id.listViewValue);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewScoreResult = (TextView) findViewById(R.id.textViewScoreResult);
        questionTotalResult = (TextView) findViewById(R.id.questionTotalResult);
        textViewRemember = (TextView) findViewById(R.id.textViewRemember);
        textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        imageView0 = (ImageView) findViewById(R.id.imageView0);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        startMenu = (RelativeLayout) findViewById(R.id.startMenu);
        mainContain = (RelativeLayout) findViewById(R.id.mainContain);
        scoreBoard = (RelativeLayout) findViewById(R.id.scoreBoard);
        valueBoard = (RelativeLayout) findViewById(R.id.valueBoard);
        buttonStart = (ImageView) findViewById(R.id.buttonStart) ;
        textFood1 = (TextView) findViewById(R.id.textFood1);
        textFood2 = (TextView) findViewById(R.id.textFood2);
        textFood3 = (TextView) findViewById(R.id.textFood3);
        textFood4 = (TextView) findViewById(R.id.textFood4);
        textViewRank = (TextView) findViewById(R.id.textViewRank);
        mediaPlayer = MediaPlayer.create(this, R.raw.tap);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.finish);

        generateListValue();

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.itemrow, dataItems);
        listViewValue.setAdapter(customAdapter);

    }
}
