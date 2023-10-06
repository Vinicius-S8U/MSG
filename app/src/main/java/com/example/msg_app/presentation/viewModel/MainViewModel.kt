package com.example.msg_app.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msg_app.data.handler.wrapper.ResultWrapper
import com.example.msg_app.domain.entity.GuestSessionRequest
import com.example.msg_app.domain.entity.MovieResponse
import com.example.msg_app.domain.usecase.GetGuestUserUseCase
import com.example.msg_app.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.msg_app.domain.usecase.GetPopularMoviesUseCase
import com.example.msg_app.domain.usecase.GetTopRatedMoviesUseCase
import com.example.msg_app.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val getGuestUserUseCase: GetGuestUserUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    private val _guestUser: MutableLiveData<GuestSessionRequest> = MutableLiveData()
    var guestUser: MutableLiveData<GuestSessionRequest> = _guestUser

    private val _popularMovies: MutableLiveData<MovieResponse> = MutableLiveData()
    var popularMovies: MutableLiveData<MovieResponse> = _popularMovies

    private val _topRatedMovies: MutableLiveData<MovieResponse> = MutableLiveData()
    var topRatedMovies: MutableLiveData<MovieResponse> = _topRatedMovies

    private val _nowPlaying: MutableLiveData<MovieResponse> = MutableLiveData()
    var nowPlaying: MutableLiveData<MovieResponse> = _nowPlaying

    private val _upcomingMovies: MutableLiveData<MovieResponse> = MutableLiveData()
    var upcomingMovies: MutableLiveData<MovieResponse> = _upcomingMovies

    fun getGuestUserRequest(accessKey: String) {
        viewModelScope.launch {
            when (val response = getGuestUserUseCase(accessKey)) {
                is ResultWrapper.Success -> {
                    _guestUser.postValue(response.content)
                }

                is ResultWrapper.Error -> {
                    Log.d("ERROR", "error getting guest User")
                }
            }
        }
    }

    fun getPopularMovies(accessKey: String) {
        viewModelScope.launch {
            when (val response = getPopularMoviesUseCase(accessKey)) {
                is ResultWrapper.Success -> {
                    _popularMovies.postValue(response.content)
                }
                is ResultWrapper.Error -> Log.d("Error","error gettings Popular movies")
            }
        }
    }


    fun getTopRatedMovies(accessKey: String) {
        viewModelScope.launch {
            when (val response = getTopRatedMoviesUseCase(accessKey)) {
                is ResultWrapper.Success -> {
                    _topRatedMovies.postValue(response.content)
                }
                is ResultWrapper.Error -> Log.d("Error","error gettings Popular movies")
            }
        }
    }


    fun getNowPlayingMovies(accessKey: String) {
        viewModelScope.launch {
            when (val response = getNowPlayingMoviesUseCase(accessKey)) {
                is ResultWrapper.Success -> {
                    _nowPlaying.postValue(response.content)
                }
                is ResultWrapper.Error -> Log.d("Error","error gettings Popular movies")
            }
        }
    }

    fun getUpcomingMovies(accessKey: String) {
        viewModelScope.launch {
            when (val response = getUpcomingMoviesUseCase(accessKey)) {
                is ResultWrapper.Success -> {
                    _upcomingMovies.postValue(response.content)
                }
                is ResultWrapper.Error -> Log.d("Error","error gettings Popular movies")
            }
        }
    }

}