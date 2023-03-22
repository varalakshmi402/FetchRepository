package com.example.fetch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HiringViewModel:ViewModel() {

    private val repository: HiringRepository = HiringRepository()

    private val _fetchRewardsItemList = MutableLiveData<List<Hiring>>()
    val fetchRewardsItemList: LiveData<List<Hiring>>
        get() = _fetchRewardsItemList

    fun fetchItems(){
        GlobalScope.launch(Dispatchers.IO){
            _fetchRewardsItemList.postValue(repository.fetchItems())
        }

    }
}
