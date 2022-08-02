package com.example.solitaireappli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

import com.example.solitaireappli.controller.DownTouchSolitaire;
import com.example.solitaireappli.controller.SolitaireController;
import com.example.solitaireappli.model.SolitaireModel;
import com.example.solitaireappli.view.SolitaireView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SolitaireView solitaireView = findViewById(R.id.solitaireView);
        SolitaireModel solitaireModel = new SolitaireModel();
        solitaireView.setSolitaireModel(solitaireModel);

        SolitaireController solitaireController = new SolitaireController(solitaireView,solitaireModel);

solitaireView.setSolitaireController(solitaireController);


    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            =new DownTouchSolitaire();
            .TouchSpider(this.spiderView,this.spiderController,event.getX(),event.getY());
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            moveTouchSpider=new MoveTouchSpider();
            moveTouchSpider.TouchSpider(this.spiderView,this.spiderController,event.getX(),event.getY());
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            upTouchSpider=new UpTouchSpider();
            upTouchSpider.TouchSpider(this.spiderView,this.spiderController,event.getX(),event.getY());
        }


        return super.onTouchEvent(event);
    }

     */
}