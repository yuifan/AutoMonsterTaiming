package com.pscomp.automonstertaiming;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.ImageView;

public class StarView extends ImageView {

    private Context mContext = null;
    public StarView(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect();
        rect.set(500, 500, 800, 800);
        canvas.drawBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.star), null, rect, null);
        super.onDraw(canvas);
    }
}
