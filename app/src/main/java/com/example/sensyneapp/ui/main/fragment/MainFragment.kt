package com.example.sensyneapp.ui.main.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.sensyneapp.R
import com.example.sensyneapp.BR
import com.example.sensyneapp.databinding.MainFragmentBinding
import com.example.sensyneapp.ui.ViewModelProviderFactory
import com.example.sensyneapp.ui.base.BaseFragment
import javax.inject.Inject

class MainFragment: BaseFragment<MainFragmentBinding, MainViewModel>(), MainNavigator {

    companion object {

        const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }

    @Inject
    internal lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MainViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.main_fragment

    override fun getViewModel(): MainViewModel {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.setNavigator(this)
        return viewModel
    }


}