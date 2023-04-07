package com.foodandservice.presentation.ui.bookings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.restaurant.GetBookingsUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BookingsViewModel(private val getBookingsUseCase: GetBookingsUseCase) :
    ViewModel() {
    private val _bookingsState = MutableSharedFlow<BookingsState>(replay = 10)
    val bookingsState: SharedFlow<BookingsState> = _bookingsState.asSharedFlow()

    init {
        getFavouriteRestaurants()
    }

    private fun getFavouriteRestaurants() {
        viewModelScope.launch {
            _bookingsState.emit(BookingsState.Loading)

            when (val bookings = getBookingsUseCase()) {
                is ApiResponse.Success -> {
                    bookings.data?.let { myBookings ->
                        _bookingsState.emit(BookingsState.Success(bookings = myBookings))
                    }
                }
                is ApiResponse.Failure -> {
                    _bookingsState.emit(
                        BookingsState.Error(
                            message = bookings.exception?.message
                                ?: "Something went wrong"
                        )
                    )
                }
            }

            _bookingsState.emit(BookingsState.Idle)
        }
    }
}