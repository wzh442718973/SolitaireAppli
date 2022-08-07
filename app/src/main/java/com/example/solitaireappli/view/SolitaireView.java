package com.example.solitaireappli.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.solitaireappli.R;
import com.example.solitaireappli.controller.DownTouchSolitaire;
import com.example.solitaireappli.controller.MoveTouchSolitaire;
import com.example.solitaireappli.controller.SolitaireController;
import com.example.solitaireappli.controller.UpTouchSolitaire;
import com.example.solitaireappli.model.Card;
import com.example.solitaireappli.model.LoadParameters;
import com.example.solitaireappli.model.Rank;
import com.example.solitaireappli.model.SolitaireModel;
import com.example.solitaireappli.model.Suit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SolitaireView extends RelativeLayout implements Observer, IGameInitView, ICollisionGame, ICollisionGameWithColumns, ICollisionGamePioche, ICollisionGameDefausse, ICollisionGamePiles{

    DownTouchSolitaire downTouchSolitaire;
    MoveTouchSolitaire moveTouchSolitaire;
    UpTouchSolitaire upTouchSolitaire;

    SolitaireController solitaireController;

    private Button buttonBack;
    private Button buttonNew;

    public float xTaken = 0.f;
    public float yTaken = 0.f;

    public float xEvent = 0.f;
    public float yEvent = 0.f;

    private CardCollectionView piocheView;
    private CardCollectionView defausseView;

    private List<CardCollectionView> pilesView;
    private List<CardCollectionView> deckView;
    private CardCollectionView takenView;

    private WinView winView;

    public ISetParameters getSetParameters() {
        return setParameters;
    }

    public ISetParameters setParameters = new SetParameters();

    public ISetParametersTaken setParametersTaken = new SetParametersTaken();

    public ISetParametersTaken getSetParametersTaken() {
        return setParametersTaken;
    }

    public CardCollectionView getTakenView() {
        return takenView;
    }

    public SolitaireController getSolitaireController() {
        return solitaireController;
    }

    public void setSolitaireController(SolitaireController solitaireController) {
        this.solitaireController = solitaireController;
    }

    LayoutInflater mInflater;
    private RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    View rootView;



    RelativeLayout header;
    RelativeLayout body;
    RelativeLayout footer;

    SolitaireModel solitaireModel;

    TextView textViewDev;

    public SolitaireView(Context context) {
        super(context);
    }

    public SolitaireView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public SolitaireView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SolitaireView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    public void update(Observable observable, Object o) {



     //   footer.removeAllViews();

        updateHeader(observable);
        updateBody(observable);
        updateFooter(observable);
    }


    private void  updateHeader(Observable observable)
    {
      //  header.removeAllViews();
       SolitaireModel model = (SolitaireModel) observable;


    }

    private void  updateBody(Observable observable)
    {
        body.removeAllViews();
        SolitaireModel model = (SolitaireModel) observable;

piocheView.clear();
        CardView cardViewPioche = model.getPioche().size()>0 ? new CardView(this.getContext(),model.getPioche().getCard(0)) : new CardView(this.getContext(),new Card(Suit.NONE, Rank.NONE,false));
        body.addView(cardViewPioche,params);
        cardViewPioche.setX(setParameters.getxPioche());
        cardViewPioche.setY(setParameters.getyPioche());
        piocheView.addCardView(cardViewPioche);


defausseView.clear();
        if(model.getDefausse().size()>0)
        {
            CardView cardView = new CardView(this.getContext(),model.getDefausse().getLastCard());
            body.addView(cardView,params);
            cardView.setX(setParameters.getxDefausse());
            cardView.setY(setParameters.getyDefausse());
            defausseView.addCardView(cardView);
        }

        else
        {
            CardView cardView = new CardView(this.getContext(),new Card(Suit.NONE, Rank.NONE,false));
            body.addView(cardView,params);
            cardView.setX(setParameters.getxDefausse());
            cardView.setY(setParameters.getyDefausse());
            defausseView.addCardView(cardView);
        }

pilesView.clear();
        for(int i=0;i<model.getPiles().size();i++)
        {
            CardCollectionView pileCollection = new ArrayCardCollectionView();
            Card card = model.getPiles().get(i).size() == 0  ? new Card(Suit.NONE, Rank.NONE,false) : model.getPiles().get(i).getLastCard();
            CardView cardView = new CardView(this.getContext(),card);
            body.addView(cardView,params);
            cardView.setX(setParameters.getxPiles() + i* setParameters.getdXPiles());
            cardView.setY(setParameters.getyPiles() + i* setParameters.getdYPiles());
            pileCollection.addCardView(cardView);
            pilesView.add(pileCollection);
        }

        deckView.clear();
        CardCollectionView cardCollectionView;
        for(int i=0;i<model.getDeck().size();i++)
        {
            cardCollectionView = new ArrayCardCollectionView();
            for(int j=0;j<model.getDeck().get(i).size();j++)
            {
                Card card = model.getDeck().get(i).getCard(j);
                //Card card = model.getPiles().get(i).size() == 0  ? new Card(Suit.NONE, Rank.NONE,false) : model.getPiles().get(i).getLastCard();
                CardView cardView = new CardView(this.getContext(),card);
                body.addView(cardView,params);
                cardView.setX(setParameters.getxDeck() + i* setParameters.getdXDeck());
                cardView.setY(setParameters.getyDeck() + j* setParameters.getdYDeck());
                cardCollectionView.addCardView(cardView);
            }
            deckView.add(cardCollectionView);


        }
        takenView.clear();
        for(int i=0;i<model.getTaken().size();i++)
        {
Card card = model.getTaken().getCard(i);
            //Card card = model.getPiles().get(i).size() == 0  ? new Card(Suit.NONE, Rank.NONE,false) : model.getPiles().get(i).getLastCard();
            CardView cardView = new CardView(this.getContext(),card);
            body.addView(cardView,params);
            cardView.setX(xTaken);
            cardView.setY(yTaken + i*setParameters.getdYDeck());
            takenView.addCardView(cardView);
        }

        if(model.isWin())
        {

            body.addView(winView,params);
            winView.setX(setParameters.getxWin());
            winView.setY(setParameters.getyWin());
            body.bringChildToFront(winView);
        }
//        for(int i=0;i<model.getDeck().size();i++)
//        {
//            for(int j=0;j<model.getDeck().get(i).size();j++)
//            {
//                Card card = model.getDeck().get(i).getCard(j);
//                //Card card = model.getPiles().get(i).size() == 0  ? new Card(Suit.NONE, Rank.NONE,false) : model.getPiles().get(i).getLastCard();
//                CardView cardView = new CardView(this.getContext(),card);
//                body.addView(cardView,params);
//                cardView.setX(setParameters.xDeck + i* setParameters.dXDeck);
//                cardView.setY(setParameters.yDeck + j* setParameters.dYDeck);
//            }
//
//        }
    }
    private void  updateFooter(Observable observable)
    {

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }



    @Override
    public void init(Context context) {
        rootView = inflate(context, R.layout.solitaire_view, this);

        header = findViewById(R.id.header);
        body = findViewById(R.id.body);
        footer = findViewById(R.id.footer);

        textViewDev = findViewById(R.id.textViewDev);
        textViewDev.setText("Hello !");

        piocheView = new ArrayCardCollectionView();
        defausseView = new ArrayCardCollectionView();
        pilesView = new ArrayList<CardCollectionView>();
        deckView = new ArrayList<CardCollectionView>();

        takenView = new ArrayCardCollectionView() ;
winView = new WinView(context);
        solitaireModel = new SolitaireModel();

buttonBack = findViewById(R.id.buttonBack);
buttonNew = findViewById(R.id.buttonNew);


buttonNew.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        solitaireController.nouveauJeu();
    }
});

buttonBack.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        solitaireController.lastMove();
    }
});
//header.addView(buttonBack);
//header.addView(buttonNew);



    }

    public void setSolitaireModel(SolitaireModel model)
    {
        this.solitaireModel = model;
        this.solitaireModel.addObserver(this);
        this.solitaireModel.loadGame(new LoadParameters());
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

       // assert(Math.abs(getCollisionPiocheItem().getXCollision() - (piocheView.get(0).getXCollision() + this.getX0()) )<10.f);
            if(e.getAction() == MotionEvent.ACTION_DOWN)
            {
                downTouchSolitaire=new DownTouchSolitaire();
                downTouchSolitaire.TouchSolitaire(this,this.solitaireController,e.getX(),e.getY());
            }
            if(e.getAction() == MotionEvent.ACTION_MOVE)
            {
                moveTouchSolitaire=new MoveTouchSolitaire();
                moveTouchSolitaire.TouchSolitaire(this,this.solitaireController,e.getX(),e.getY());
            }
            if(e.getAction() == MotionEvent.ACTION_UP)
            {
                upTouchSolitaire=new UpTouchSolitaire();
                upTouchSolitaire.TouchSolitaire(this,this.solitaireController,e.getX(),e.getY());
            }

            return true;
    }


    public void setDevText(String str)
    {
        textViewDev.setText(str);
    }


    @Override
    public List<ICollisionItem> getListCollisionItems() {
        List<ICollisionItem> cardViews = new ArrayList<ICollisionItem>();
        for(int i=0;i<deckView.size();i++)
        {
            for(int j=deckView.get(i).size()-1;j>=0;j--)
            {
                cardViews.add(deckView.get(i).get(j));
            }
        }


        return cardViews;
    }

    @Override
    public String findCollisionItem(ICollisionItem iCollisionItem) {

        for(int i=0;i<deckView.size();i++)
        {
            for(int j=0;j<deckView.get(i).size();j++)
            {
                if((deckView.get(i).get(j)).equals(iCollisionItem))
                {
                    return i + "_" + j;
                }
            }
        }
        return "";

    }

    @Override
    public float getX0() {
        return body.getX();
    }

    @Override
    public float getY0() {
        return body.getY();
    }


    public void setToForeGround(CardCollectionView cardCollectionView) {
        for(int i=0;i<cardCollectionView.size();i++)
        {
            body.bringChildToFront(cardCollectionView.get(i));
        }


    }

    @Override
    public List<ICollisionItem> getListCollisionColumnsItems() {
        List<ICollisionItem> cardViews = new ArrayList<ICollisionItem>();
        for(int i=0;i<deckView.size();i++) {
            if(deckView.get(i).size()>0)
            {
                cardViews.add((deckView.get(i)).getLastCardView());
            }
            else
            {
                CardView emptyCV = new CardView(this.getContext());
                emptyCV.setX(setParameters.getxDeck() + i*setParameters.getdXDeck() );
                emptyCV.setY(setParameters.getyDeck());
                cardViews.add(emptyCV);
            }
        }

        return cardViews;
    }

    @Override
    public ICollisionItem getCollisionDefausseItem() {
        return defausseView.getLastCardView();
    }

    @Override
    public ICollisionItem getCollisionPiocheItem() {
        return piocheView.getLastCardView();
    }

    @Override
    public List<ICollisionItem> getListCollisionPilesItems() {
        List<ICollisionItem> cardViews = new ArrayList<ICollisionItem>();
        for(int i=0;i<pilesView.size();i++)
            cardViews.add(pilesView.get(i).getLastCardView());
        return cardViews;
    }
}
