package io.genemoz.fileviewer.videoView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class AspectRatioFrameLayout extends FrameLayout {

    private static final float ASPECT_RATIO = 10f / 16f;

    public AspectRatioFrameLayout(Context context) {
        super(context);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = MeasureSpec.getSize(widthMeasureSpec);

        int calculatedHeight = (int) (originalWidth * ASPECT_RATIO);

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(calculatedHeight, MeasureSpec.EXACTLY)
        );
    }
}
