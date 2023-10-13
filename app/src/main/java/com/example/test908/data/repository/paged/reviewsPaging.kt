package com.example.test908.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.test908.data.model.Critic
import com.example.test908.data.model.Result
import com.example.test908.network.serviceRetrofit
import com.example.test908.utils.constant.API_KEY
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class reviewsPaging @Inject constructor(private val service: serviceRetrofit): PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage= params.key?:0
            val prevKey = if (currentPage > 0) currentPage - 1 else null
            val response:Response<Critic> =   service.getreview("$API_KEY\n" +
                    "\n",currentPage)



            val data= response.body()?.results
            val responsedata = mutableListOf<Result>()
            val nextKey = if (response.body()?.results?.isEmpty() == true) currentPage + 1 else null
            data.let {
                if (it != null) {
                    responsedata.addAll(it)
                }
            }
            LoadResult.Page(
                data = responsedata,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }catch (e:java.lang.Exception){
            return LoadResult.Error(e)
        }catch (e:HttpException){
            return LoadResult.Error(e)
        }
    }
}
