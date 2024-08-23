package com.example.clock.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class NonSwipeableViewPager(context: Context, attrs: AttributeSet? = null) : ViewPager(context,attrs) {
    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        // Do not intercept touch events
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Do not handle touch events
        return false
    }

}