package com.example.sensyneapp.ui.main.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensyneapp.R
import com.example.sensyneapp.BR
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.databinding.MainFragmentBinding
import com.example.sensyneapp.ui.ViewModelProviderFactory
import com.example.sensyneapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospital_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel.loadData()
    }

    override fun showHospitals(response: List<Hospital>) {
        hospital_list.apply {
            adapter = MainAdapter(response){
                Toast.makeText(context, "Click ${it.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}