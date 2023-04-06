package com.foodandservice.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.foodandservice.data.remote.model.restaurant.RestaurantDto
import com.foodandservice.data.remote.service.CustomerService
import com.foodandservice.domain.model.location.Coordinate
import retrofit2.HttpException
import java.io.IOException

private const val RESTAURANTS_STARTING_PAGE_INDEX = 1

class RestaurantPagingSource(
    private val customerService: CustomerService,
    private val coordinate: Coordinate
) :
    PagingSource<Int, RestaurantDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RestaurantDto> {
        val page = params.key ?: RESTAURANTS_STARTING_PAGE_INDEX

        return try {
            val restaurants = customerService.getRestaurants(
                latitude = coordinate.latitude,
                longitude = coordinate.longitude,
                page = page,
                limit = params.loadSize
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