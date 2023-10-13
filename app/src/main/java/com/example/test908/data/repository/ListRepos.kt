package com.example.test908.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ListRepos {
    fun getList(): Flow<PagingData<com.example.test908.data.model.Result>>
}