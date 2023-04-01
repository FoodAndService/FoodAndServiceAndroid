package com.foodandservice.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.foodandservice.data.remote.model.restaurant.RestaurantDto
import com.foodandservice.data.remote.service.CustomerService
import retrofit2.HttpException
import java.io.IOException

private const val RESTAURANTS_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 5
private const val EXAMPLE_LATITUDE = 40.4167754
private const val EXAMPLE_LONGITUDE = -3.7037902

class RestaurantPagingSource(
    //private val getUserLocationUseCase: GetUserLocationUseCase,
    private val customerService: CustomerService
) :
    PagingSource<Int, RestaurantDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RestaurantDto> {
        val page = params.key ?: RESTAURANTS_STARTING_PAGE_INDEX

        return try {
            val restaurants = customerService.getRestaurants(
                latitude = EXAMPLE_LATITUDE,
                longitude = EXAMPLE_LONGITUDE,
                page = page,
                limit = NETWORK_PAGE_SIZE
            )

            val prevKey = if (page == RESTAURANTS_STARTING_PAGE_INDEX) null else page
            val nextKey = if (restaurants.isEmpty()) null else page + 1

            LoadResult.Page(
                data = restaurants,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RestaurantDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}