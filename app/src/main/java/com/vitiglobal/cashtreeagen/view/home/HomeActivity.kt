package com.vitiglobal.cashtreeagen.view.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vitiglobal.cashtreeagen.databinding.ActivityHomeBinding
import com.vitiglobal.cashtreeagen.utils.MyDialog
import com.vitiglobal.cashtreeagen.utils.MyToast
import com.vitiglobal.cashtreeagen.view.home.agent.AgentActivity

class HomeActivity : AppCompatActivity() {
    private var binding: ActivityHomeBinding? = null
    private var viewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init binding & viewModel
        binding = ActivityHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        setContentView(binding!!.root)

        initView()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Set binding & viewModel to null
        binding = null
        viewModel = null
    }

    private fun initView() {
        with(binding!!) {
            vpBanner.adapter = HomeBannerAdapter()
            ibBack.setOnClickListener {
                finish()
            }

            rlAgent.setOnClickListener {
                val intent = Intent(binding!!.root.context, AgentActivity::class.java)
                startActivity(intent)
            }

            rlMiniGame.setOnClickListener {
                viewModel!!.getFact()
            }
        }
    }

    private fun observeViewModel() {
        with(viewModel!!) {
            onProgress.observe(this@HomeActivity) {
                MyDialog.showDialogLoader(binding!!.root.context, it)
            }

            catFact.observe(this@HomeActivity) {
                Handler(Looper.getMainLooper()).postDelayed({
                    MyDialog.showDialogCatFact(binding!!.root.context, it, ::getFact)
                }, 210)
            }

            apiResult.observe(this@HomeActivity) {
                when {
                    it.error != null -> MyToast.show(binding!!.root.context, getString(it.error))
                    it.errorMessage != null -> MyToast.show(binding!!.root.context, it.errorMessage)
                }
            }
        }
    }
}