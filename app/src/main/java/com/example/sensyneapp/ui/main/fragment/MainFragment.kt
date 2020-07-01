package com.example.sensyneapp.ui.main.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensyneapp.R
import com.example.sensyneapp.BR
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.databinding.MainFragmentBinding
import com.example.sensyneapp.ui.ViewModelProviderFactory
import com.example.sensyneapp.ui.base.BaseFragment
import com.example.sensyneapp.ui.details.DetailsActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)

        val menuItem = menu.findItem(R.id.action_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchTextListener = object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        }


        val closeSearchListener = SearchView.OnCloseListener {
            viewModel.search(null)
            false
        }

        val searchView = menuItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.setOnQueryTextListener(searchTextListener);
        searchView.setOnCloseListener(closeSearchListener)
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun showHospitals(items: List<Hospital>?) {
        items?.let {
            hospital_list.apply {
                adapter = MainAdapter(items){
                    startActivity(DetailsActivity.createIntent(context, it))
                }
            }
        }
    }

}