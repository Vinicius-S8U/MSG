package com.example.msg_app.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.msg_app.R
import com.example.msg_app.domain.entity.MovieResponse
import com.example.msg_app.presentation.theme.MSGappTheme
import com.example.msg_app.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var accessKey: String
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            accessKey = "Bearer ${resources.getString(R.string.tmbd_key)}"
            MSGappTheme {
                viewModel = hiltViewModel()
                val movies by viewModel.popularMovies.observeAsState()
                moviesColumn(movies = movies)

                viewModel.getPopularMovies(accessKey)
            }
        }
    }
}

@Composable
fun moviesColumn(movies: MovieResponse?) {
    LazyColumn {
        if (movies != null) {
            items(movies.results) {
                Text(text = "Esses filmes são ${it.title}")
            }
        }
    }
}





