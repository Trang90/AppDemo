package com.asiasquare.byteg.shoppingdemo.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.asiasquare.byteg.shoppingdemo.databinding.FragmentCatalogBinding
import com.asiasquare.byteg.shoppingdemo.datamodel.Catalog

/**
    First fragment of the app.
    Display a list of catalog for user to choose.
 **/
class CatalogFragment : Fragment() {

    /** binding will only exist between onAttach and on Detach **/
    private var _binding : FragmentCatalogBinding? = null
    private val binding get() = _binding!!



    /**
     * Create viewModel, provide application to Factory to create an AndroidViewModel class
     */
    private val viewModel: CatalogFragmentViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,CatalogFragmentViewModel.Factory(activity.application))
            .get(CatalogFragmentViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatalogBinding.inflate(inflater,container,false)

        /** Create recyclerView adapter and define OnClickListener **/
        val adapter = CatalogFragmentAdapter(CatalogFragmentAdapter.OnClickListener{
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
             })

        binding.rvMainCatalog.adapter = adapter

        /** Update data to adapter **/
        viewModel.catalogList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onClick(catalog: Catalog) {
        view?.findNavController()?.navigate(CatalogFragmentDirections.actionCatalogFragmentToItemListFragment(catalog))

    }


    /**Remove _binding when fragment is destroy**/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}