package com.example.test908.ui

import androidx.lifecycle.*
import androidx.paging.*
import com.example.test908.data.model.Result
import com.example.test908.data.repository.RepositoryImpl
import com.example.test908.data.repository.reviewsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class fragViewModel @Inject constructor(val repository: RepositoryImpl): ViewModel() {
    private val listRew = MutableLiveData<PagingData<Result>>()
    suspend fun getList(): LiveData<PagingData<Result>> {
        val response = repository.getList().cachedIn(viewModelScope)
        listRew.value = response.asLiveData().value
        return response.stateIn(viewModelScope).asLiveData()
    }
}