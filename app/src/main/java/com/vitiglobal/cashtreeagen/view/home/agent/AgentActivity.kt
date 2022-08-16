package com.vitiglobal.cashtreeagen.view.home.agent

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vitiglobal.cashtreeagen.databinding.ActivityAgentBinding
import com.vitiglobal.cashtreeagen.utils.MyDialog

class AgentActivity : AppCompatActivity() {
    private var binding: ActivityAgentBinding? = null
    private var viewModel: AgentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init binding & viewModel
        binding = ActivityAgentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AgentViewModel::class.java]
        setContentView(binding!!.root)

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Set binding & viewModel to null
        binding = null
        viewModel = null
    }

    private fun initView() {
        with(binding!!) {
            ibBack.setOnClickListener {
                finish()
            }

            ivShowPassword.setOnClickListener {
                ivShowPassword.visibility = View.GONE
                ivHidePassword.visibility = View.VISIBLE
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }

            ivHidePassword.setOnClickListener {
                ivShowPassword.visibility = View.VISIBLE
                ivHidePassword.visibility = View.GONE
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }

            ivShowConfirmPassword.setOnClickListener {
                ivShowConfirmPassword.visibility = View.GONE
                ivHideConfirmPassword.visibility = View.VISIBLE
                etConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }

            ivHideConfirmPassword.setOnClickListener {
                ivShowConfirmPassword.visibility = View.VISIBLE
                ivHideConfirmPassword.visibility = View.GONE
                etConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }

            tvButtonLater.setOnClickListener {
                finish()
            }

            tvCreatePassword.setOnClickListener {
                val password = etPassword.text.toString()
                val confirmPassword = etConfirmPassword.text.toString()

                tvErrorMessagePassword.visibility = View.INVISIBLE
                tvErrorMessageConfirmPassword.visibility = View.INVISIBLE

                if (password.isEmpty()) {
                    etPassword.requestFocus()

                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(etPassword, InputMethodManager.SHOW_IMPLICIT)
                } else if (password.length < 8) {
                    tvErrorMessagePassword.visibility = View.VISIBLE
                } else if (confirmPassword.isEmpty()) {
                    etConfirmPassword.requestFocus()

                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(etConfirmPassword, InputMethodManager.SHOW_IMPLICIT)
                } else if (password != confirmPassword) {
                    tvErrorMessageConfirmPassword.visibility = View.VISIBLE
                } else {
                    MyDialog.showDialogCreateDialogSuccess(root.context)
                }
            }
        }
    }
}