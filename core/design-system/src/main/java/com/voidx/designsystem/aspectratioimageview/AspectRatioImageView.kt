package com.voidx.designsystem.aspectratioimageview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class AspectRatioImageView : ImageView {

    var paddingRightLeft: Int = 0

    var aspectRatio: Float = 1f

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth - paddingRightLeft * 2
        setMeasuredDimension(width, (width / aspectRatio).toInt())
    }
}
