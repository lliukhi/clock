package com.example.clock.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class ScrollAlphabetSidebar : View {
    private var paint: Paint? = null
    private val letters = arrayOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z"
    )

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint!!.color = Color.BLACK
        paint!!.textSize = 20f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val height = height
        val width = width
        val singleHeight = height / letters.size

        for (i in letters.indices) {
            val x = width / 2 - paint!!.measureText(letters[i]) / 2
            val y = (singleHeight * i + singleHeight).toFloat()
            canvas.drawText(letters[i], x, y, paint!!)
        }
    }
}
