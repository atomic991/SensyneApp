package com.example.sensyneapp.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sensyneapp.R
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.ui.base.BaseActivity
import com.example.sensyneapp.ui.details.fragment.DetailsFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DetailsActivity: BaseActivity(), HasAndroidInjector {

    companion object {

        private const val EXTRA_HOSPITAL = "EXTRA_HOSPITAL"

        fun createIntent(context: Context?, hospital: Hospital): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_HOSPITAL, hospital)
            return intent
        }
    }

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(intent.getParcelableExtra(
                    EXTRA_HOSPITAL)), DetailsFragment.TAG)
                .commitNow()
        }
    }
}