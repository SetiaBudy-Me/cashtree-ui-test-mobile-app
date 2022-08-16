package com.vitiglobal.cashtreeagen.utils

import android.content.Context
import android.widget.Toast


@Suppress("unused")
class MyToast {
    companion object {
        private var tag = Constants.TAG + " " + this::class.java.simpleName

        fun show(context: Context, text: String?) {
            if(text != null) Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }
}