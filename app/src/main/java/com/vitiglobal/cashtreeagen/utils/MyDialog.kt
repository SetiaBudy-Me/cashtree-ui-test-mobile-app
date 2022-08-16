package com.vitiglobal.cashtreeagen.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitiglobal.cashtreeagen.R
import com.vitiglobal.cashtreeagen.data.CatFact

class MyDialog {
    companion object {
        private var tag = Constants.TAG + " " + this::class.java.simpleName

        private lateinit var dialog: Dialog

        fun showDialogLoader(context: Context, visible: Boolean) {
            try {
                if(visible) {
                    dialog = Dialog(context)
                    dialog = Dialog(context, R.style.CustomAlertDialog)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setContentView(R.layout.dialog_loading)
                    dialog.setCancelable(false)
                    dialog.show()
                }else{
                    dialog.dismiss()
                }
            }catch (e: Exception){
                Log.e(tag, "Exception: ${e.localizedMessage}")
            }
        }

        fun showDialogCreateDialogSuccess(context: Context) {
            val contentView = View.inflate(context, R.layout.dialog_create_password_success, null)
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(contentView)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setOnShowListener { dia ->
                val bottomSheetDialog = dia as BottomSheetDialog
                val bottomSheetInternal: FrameLayout = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
                bottomSheetInternal.setBackgroundResource(R.drawable.draw_round_top)
            }

            val tvButtonOk = dialog.findViewById<TextView>(R.id.tv_button_ok)
            tvButtonOk?.setOnClickListener {
                dialog.dismiss()

                val activity = context as Activity
                activity.finish()
            }

            dialog.show()
        }

        fun showDialogCatFact(context: Context, catFact: CatFact, reload: () -> Unit) {
            val contentView = View.inflate(context, R.layout.dialog_cat_fact, null)
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(contentView)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setOnShowListener { dia ->
                val bottomSheetDialog = dia as BottomSheetDialog
                val bottomSheetInternal: FrameLayout = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
                bottomSheetInternal.setBackgroundResource(R.drawable.draw_round_top)
            }

            val tvFact = dialog.findViewById<TextView>(R.id.tv_fact)
            val tvButtonReload = dialog.findViewById<TextView>(R.id.tv_button_reload)


            tvFact?.text = catFact.fact
            tvButtonReload?.setOnClickListener {
                dialog.dismiss()
                reload()
            }

            dialog.show()
        }
    }
}