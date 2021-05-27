package com.asiasquare.byteg.shoppingdemo.catalog

import android.app.Application
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.datamodel.Catalog

class CatalogFragmentViewModel(application: Application) : AndroidViewModel(application){

    /**
     * List of catalog, observe this to get tha change in database
     */
    private val _catalogList = MutableLiveData<List<Catalog>>()
    val catalogList : MutableLiveData<List<Catalog>>
        get() = _catalogList

    //add a handler for the click event
//    private val _navigateToItemList = MutableLiveData<Int?>()
//    val navigateToItemList
//        get() = _navigateToItemList
//
//    //Initiate navigation
//    fun onCatalogClicked(id: Int) {
//        _navigateToItemList.value = id
//    }
//
//    //set it to null once navigation is completed
//    fun onItemListNavigated() {
//        _navigateToItemList.value = null
//    }

    private val _navigateToSleepQuality = MutableLiveData<Catalog?>()
    val navigateToSleepQuality: LiveData<Catalog?>
        get() = _navigateToSleepQuality

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }





    init {
        generateDummyList()
    }


    /**
     * Create dummy list for testing
     */
    private fun generateDummyList(){
        val catalogList = mutableListOf<Catalog>()

        catalogList.add(Catalog(0,"Gạo & mì các loại", R.drawable.ct_bungao))
        catalogList.add(Catalog(1,"Thực phẩm đông lạnh", R.drawable.ct_donglanh))
        catalogList.add(Catalog(2,"Gia vị", R.drawable.ct_nuoccham))
        catalogList.add(Catalog(3,"Rau, củ, quả", R.drawable.ct_raucu))
        catalogList.add(Catalog(4,"Đồ khô & ăn vặt", R.drawable.ct_dokho))
        catalogList.add(Catalog(5,"Thực phẩm đóng hộp", R.drawable.ct_dohop))

        _catalogList.value= catalogList
    }


    /**
     * Factory for constructing CatalogFragmentViewModel with parameter
     */
    class Factory(private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CatalogFragmentViewModel::class.java)){
                return CatalogFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}