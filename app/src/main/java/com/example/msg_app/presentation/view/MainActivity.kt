package com.example.msg_app.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.msg_app.R
import com.example.msg_app.domain.entity.MovieResponse
import com.example.msg_app.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var accessKey: String

    val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accessKey = "Bearer ${resources.getString(R.string.tmbd_key)}"
        setContent {
            val pagerState = rememberPagerState(pageCount = { 3 })
            HorizontalPager(state = pagerState) { pager ->
                when(pager){
                    0 -> setTmdbFrame(viewModel = viewModel, accessKey = accessKey)
                    1 -> Text(text = "ARE YOU GENIUS?????????", color = Color.White, fontSize = 47.sp)
                    2 -> Text(text = "UAUUUUUU", color = Color.White, fontSize = 47.sp)
                }
            }
        }
    }
}

@Composable
fun setTmdbFrame(viewModel: MainViewModel, accessKey: String) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        val popularMovies by viewModel.popularMovies.observeAsState()
        val topRatedMovies by viewModel.topRatedMovies.observeAsState()
        val nowPlayingMovies by viewModel.nowPlaying.observeAsState()
        val upcomingMovies by viewModel.upcomingMovies.observeAsState()
        tmbdTopBar()
        upcomingTitles()
        upcomingItems(upcomingMovies)
        topRatedTitle()
        topRatedItems(topRatedMovies)
        nowPlayingTitle()
        nowPlayingItems(nowPlayingMovies)
        popularTitles()
        popularItems(popularMovies)
    }
    viewModel.getPopularMovies(accessKey)
    viewModel.getTopRatedMovies(accessKey)
    viewModel.getNowPlayingMovies(accessKey)
    viewModel.getUpcomingMovies(accessKey)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tmbdTopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                "TMDB",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = Modifier.padding(8.dp),
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Color.White,
                    contentDescription = "Localized description",
                    modifier = Modifier
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}

@Composable
fun upcomingTitles() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Upcoming",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        Button(
            modifier = Modifier.padding(end = 16.dp, bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "All", color = Color.Black)
        }
    }
}

@Composable
fun upcomingItems(movies: MovieResponse?) {
    if (movies != null) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            val moviesFilteredList =
                movies.results.filter { movies -> movies.title.length < 18 }.shuffled()
            repeat(10) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val painter =
                        rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/" + moviesFilteredList[it].posterPath)
                    Image(
                        painter = painter,
                        contentDescription = "Movie Image",
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .height(184.dp)
                            .width(110.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = moviesFilteredList[it].title,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Clip,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .width(110.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun topRatedTitle() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Top Rated",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        Button(
            modifier = Modifier.padding(end = 16.dp, bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "All", color = Color.Black)
        }
    }
}


@Composable
fun topRatedItems(movies: MovieResponse?) {
    if (movies != null) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            val moviesFilteredList = movies.results.filter { movies -> movies.title.length < 18 }
            repeat(10) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val painter =
                        rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/" + moviesFilteredList[it].posterPath)
                    Image(
                        painter = painter,
                        contentDescription = "Movie Image",
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .height(184.dp)
                            .width(110.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = moviesFilteredList[it].title,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Clip,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .width(110.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun nowPlayingTitle() {
    Text(
        text = "Now Playing",
        color = Color.White,
        fontSize = 24.sp,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun nowPlayingItems(movies: MovieResponse?) {
    if (movies != null) {
        val moviesFilteredList = movies.results.shuffled()
        Row(
            modifier = Modifier
                .padding(start = 16.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            repeat(movies!!.results.size) {
                val bkgPainter =
                    rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/" + moviesFilteredList[it].backgroundImg)
                val painter =
                    rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/" + moviesFilteredList[it].posterPath)
                Box(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .height(200.dp)
                        .width(280.dp)
                        .paint(bkgPainter)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 26.dp, end = 8.dp)
                            .height(200.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = "poster Img",
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(0.3f)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.36f)
                                .background(
                                    Color.Black.copy(0.4f)
                                )
                        ) {
                            Text(
                                text = moviesFilteredList[it].title,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                            Text(
                                text = moviesFilteredList[it].overview,
                                color = Color.White,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 11.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun popularTitles() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Popular",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        Button(
            modifier = Modifier.padding(end = 16.dp, bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "All", color = Color.Black)
        }
    }
}


@Composable
fun popularItems(movies: MovieResponse?) {
    if (movies != null) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            val moviesFilteredList = movies.results.filter { movies -> movies.title.length < 18 }
            repeat(10) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val painter =
                        rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/" + moviesFilteredList[it].posterPath)
                    Image(
                        painter = painter,
                        contentDescription = "Movie Image",
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .height(184.dp)
                            .width(110.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = moviesFilteredList[it].title,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Clip,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .width(110.dp)
                    )
                }
            }
        }
    }
}
