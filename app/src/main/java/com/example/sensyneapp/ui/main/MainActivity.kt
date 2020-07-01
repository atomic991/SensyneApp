package com.example.sensyneapp.ui.main

import android.os.Bundle
import com.example.sensyneapp.R
import com.example.sensyneapp.ui.base.BaseActivity
import com.example.sensyneapp.ui.main.fragment.MainFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
                    .commitNow()
        }
    }
}