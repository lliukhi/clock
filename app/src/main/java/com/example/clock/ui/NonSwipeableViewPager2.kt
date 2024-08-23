package com.example.clock.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class NonSwipeableViewPager2(context: Context, attrs: AttributeSet? = null) : FrameLayout(context,attrs) {
    private val viewPager: ViewPager2
    private var initialX = 0f
    private var initialY = 0f
    private val threshold = 50

    init {
        viewPager = ViewPager2(context, attrs)
        addView(viewPager)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//       ev?.let {
//           when(ev.action){
//               MotionEvent.ACTION_DOWN ->{
//                   initialX = ev.x
//                   initialY = ev.y
//                   return super.onInterceptTouchEvent(ev)
//               }
//               MotionEvent.ACTION_MOVE -> {
//                   val diffX = ev.x - initialX
//                   val diffY = ev.y - initialY
//                   if (Math.abs(diffY) > Math.abs(diffX) && Math.abs(diffY) > threshold) {
//                       // 这是上下滑动
//                       return false
//                   }
//                   return true
//               }
//               MotionEvent.ACTION_UP ->{
//                   super.onInterceptTouchEvent(ev)
//               }
//               else->{}
//           }
        return false
       }

        // 禁止触摸事件
//        return super.onInterceptTouchEvent(ev)


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        // 禁止触摸事件
//        for (i in 0 until childCount) {
//            getChildAt(i).dispatchTouchEvent(ev)
//        }
        return false
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        viewPager.adapter = adapter
    }

    fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        viewPager.setCurrentItem(item, smoothScroll)
    }

    fun registerOnPageChangeCallback(callback: ViewPager2.OnPageChangeCallback) {
        viewPager.registerOnPageChangeCallback(callback)
    }
}