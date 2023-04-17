package com.example.picturecollector.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.picturecollector.R
import com.example.picturecollector.network.Picture
import com.example.picturecollector.ui.theme.PictureCollectorTheme

@Composable
fun HomeScreen(
    uiState: PicturesUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is PicturesUiState.Loading -> LoadingScreen(modifier)
//        is PicturesUiState.Success -> ResultScreen(uiState.pictures, modifier)
//        is PicturesUiState.Success -> PictureCard(picture = uiState.pictures, modifier = modifier)
        is PicturesUiState.Success -> PhotosGridScreen(photos = uiState.pictures, modifier)
        is PicturesUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.loading_failed))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosGridScreen(photos: List<Picture>, modifier: Modifier = Modifier) {
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(150.dp),
//        modifier = modifier.fillMaxWidth(),
//        contentPadding = PaddingValues(4.dp)
//    ) {
//        items(items = photos, key = { photo -> photo.id }) { picture ->
//            PictureCard(picture = picture)
//        }
//    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(), contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos, key = { photo -> photo.id }) { picture ->
            PictureCard(picture = picture)
        }
    }
}

@Composable
fun PictureCard(picture: Picture, modifier: Modifier = Modifier) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(picture.url)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
    val painter = rememberAsyncImagePainter(model = model)
//    val aspectRatio = painter.intrinsicSize.let { size ->
//        size.height / size.width
//    }
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
//            .aspectRatio(aspectRatio),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(context = LocalContext.current)
//                .data(picture.url)
//                .crossfade(true)
//                .build(),
//            contentDescription = stringResource(id = R.string.sex_pictures),
//            contentScale = ContentScale.FillBounds,
//            error = painterResource(id = R.drawable.ic_broken_image),
//            placeholder = painterResource(id = R.drawable.loading_img)
//        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painter,
            contentDescription = stringResource(id = R.string.sex_pictures),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun ResultScreen(uiState: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = uiState)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PhotoGridScreenPreview() {
    PictureCollectorTheme {
        val mockData = List(10) { Picture(it, "") }
        PhotosGridScreen(photos = mockData)
    }
}