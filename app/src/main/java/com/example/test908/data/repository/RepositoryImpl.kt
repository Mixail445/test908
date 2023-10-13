package com.example.test908.data.repository



import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.test908.data.model.Result
import com.example.test908.network.serviceRetrofit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class RepositoryImpl @Inject
    constructor(val service: serviceRetrofit): ListRepos{
    override fun getList(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { reviewsPaging(service = service) }
        ).flow    }


}