package com.example.sensyneapp.ui.details.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.sensyneapp.R
import com.example.sensyneapp.BR
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.databinding.FragmentDetailsBinding
import com.example.sensyneapp.ui.ViewModelProviderFactory
import com.example.sensyneapp.ui.base.BaseFragment
import javax.inject.Inject

class DetailsFragment: BaseFragment<FragmentDetailsBinding, DetailsViewModel>(), DetailsNavigator {

    companion object {

        const val TAG = "DetailsFragment"
        private const val EXTRA_HOSPITAL = "EXTRA_HOSPITAL"

        fun newInstance(hospital: Hospital?): DetailsFragment{
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_HOSPITAL, hospital)

            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    internal lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: DetailsViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_details

    override fun getViewModel(): DetailsViewModel {
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
        viewModel.setNavigator(this)
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Hospital>(EXTRA_HOSPITAL)?.let {
            viewModel.setHospitalData(it)
        }
    }

    override fun setTitle(name: String?) {
        activity?.title = name
    }
}