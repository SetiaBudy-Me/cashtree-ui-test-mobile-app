package com.vitiglobal.cashtreeagen.view.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vitiglobal.cashtreeagen.databinding.ActivityOnboardingBinding
import com.vitiglobal.cashtreeagen.utils.ZoomOutPageTransformer
import com.vitiglobal.cashtreeagen.view.home.HomeActivity

class OnboardingActivity : AppCompatActivity() {
    private var binding: ActivityOnboardingBinding? = null
    private var viewModel: OnboardingViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init binding & viewModel
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]
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
            vpOnboarding.adapter = OnboardingAdapter()
            dotsIndicator.attachTo(vpOnboarding)

            vpOnboarding.setPageTransformer { page, position ->
                ZoomOutPageTransformer().transformPage(page, position)
            }

            ivClose.setOnClickListener {
                val intent = Intent(binding!!.root.context, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}