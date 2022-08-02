package com.example.solitaireappli.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.solitaireappli.R;
import com.example.solitaireappli.model.Card;
import com.example.solitaireappli.model.Rank;
import com.example.solitaireappli.model.Suit;

import java.util.HashMap;

public class CardView extends View implements ICollisionItem{

    private Card card = new Card(Suit.DIAMOND, Rank.KING, true);
    private Bitmap mImage;
    private boolean isEmpty = false;

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CardView(Context context) {
        super(context);

        init(null);
    }

    public CardView(Context context, Card card) {
        super(context);
        this.card = card;
        init(null);
    }

    public CardView(Context context, boolean isEmpty) {
        super(context);
        //  this.card = card;
        this.isEmpty = isEmpty;
        init(null);
    }

    public CardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CardView(Context context, Card card, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.card = card;
        init(attrs);
    }

    public CardView(Context context, Card card, boolean isEmpty, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.card = card;
        this.isEmpty = isEmpty;
        init(attrs);
    }

    public CardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public CardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        if (!isEmpty) {
            mImage = new CardLoader().cardLoad(this.getContext(), this.card);
            mImage = getResizedBitmap(mImage, 100, 200);
        } else {
            mImage = BitmapFactory.decodeResource(getResources(), com.example.solitaireappli.R.drawable.empty);
            mImage = getResizedBitmap(mImage, 100, 200);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mImage, 0, 0, null);
    }

    public static int getDrawable(Context context, String name) {


        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }

    private Bitmap getResizedBitmap(Bitmap bit, int reqWidth, int reqHeight)
    {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0,0,bit.getWidth(),bit.getHeight());
        RectF dst = new RectF(0,0,reqWidth,reqHeight);

        matrix.setRectToRect(src,dst,Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bit,0,0,bit.getWidth(),bit.getHeight(),matrix,true);
    }




    @Override
    public float getXCollision() {
        return this.getX();
    }

    @Override
    public float getYCollision() {
        return this.getY();
    }

    @Override
    public float getWCollision() {
        return this.mImage.getWidth();
    }

    @Override
    public float getHCollision() {
        return this.mImage.getHeight();
    }
}