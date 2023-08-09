package kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import kr.co.fastcampus.part4plus.restaurantapp.R
import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.RestaurantDetailEntity
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.input.IDetailViewModelInputs
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output.RestaurantDetailState
import kr.co.fastcampus.part4plus.restaurantapp.ui.components.buttons.PrimaryButton
import kr.co.fastcampus.part4plus.restaurantapp.ui.components.buttons.SecondaryButton
import kr.co.fastcampus.part4plus.restaurantapp.ui.components.restaurant.RestaurantMeta
import kr.co.fastcampus.part4plus.restaurantapp.ui.theme.Paddings
import kr.co.fastcampus.part4plus.restaurantapp.ui.theme.colorScheme
import kr.co.fastcampus.part4plus.restaurantapp.ui.util.getAnnotatedText


@Composable
fun RestaurantDetailScreen(
    restaurantDetailState: State<RestaurantDetailState>,
    input: IDetailViewModelInputs
) {
    val restaurantDetail by restaurantDetailState

    if (restaurantDetail is RestaurantDetailState.Main) {
        RestaurantDetail(
            restaurant = (restaurantDetail as RestaurantDetailState.Main).detailEntity,
            input = input
        )
    }

    BackHandler {
        input.goBackToFeed()
    }
}

@Composable
fun RestaurantDetail(
    restaurant: RestaurantDetailEntity,
    input: IDetailViewModelInputs
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier.requiredHeight(70.dp),
                navigationIcon = {
                    IconButton(onClick = { input.goBackToFeed() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = Paddings.extra
                )
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            // Image
            BigImage(
                restaurant = restaurant
            )

            Row(
                modifier = Modifier.padding(
                    top = Paddings.xlarge
                )
            ) {
                // Meta
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    RestaurantMeta(
                        key = "Rating",
                        value = String.format("%.2f",restaurant.rating)
                    )

                    RestaurantMeta(
                        key = "Neighborhood",
                        value = restaurant.neighborhood
                    )
                }
                Spacer(Modifier.width(Paddings.large))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    RestaurantMeta(
                        key = "Cuisine Type",
                        value = restaurant.cuisineType
                    )

                    RestaurantMeta(
                        key = "Address",
                        value = restaurant.address
                    )
                }
            }

            // Title
            Text(
                text = getAnnotatedText(text = restaurant.name),
                modifier = Modifier.padding(
                    top = Paddings.extra,
                    bottom = Paddings.large
                ),
                style = MaterialTheme.typography.h3
            )

            // Rating
            PrimaryButton(
                modifier = Modifier
                    .padding(top = Paddings.xlarge)
                    .fillMaxWidth(),
                text = "Add Rating Score",
                onClick = {
                    input.rateClicked()
                }
            )

            // Google Map Button
            SecondaryButton(
                modifier = Modifier
                    .padding(
                        top = Paddings.xlarge,
                        bottom = Paddings.extra
                    )
                    .fillMaxWidth(),
                text = "OPEN MAP",
                onClick = {
                    input.googleMapClicked()
                }
            )

            // Review

            Text(
                text = "Reviews",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(top = Paddings.xlarge)
            )

            restaurant.reviews.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = Paddings.extra
                        ),
                    elevation = Paddings.none,
                    backgroundColor = MaterialTheme.colorScheme.backgroundVariant,
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                Paddings.xlarge
                            )
                    ) {
                        RestaurantMeta(
                            key = "Name",
                            value = it.name
                        )
                        RestaurantMeta(
                            key = "Date",
                            value = it.date
                        )
                        RestaurantMeta(
                            key = "Rating",
                            value = it.rating.toString()
                        )
                        RestaurantMeta(
                            key = "Comments",
                            value = it.comments
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnScope.BigImage(
    restaurant: RestaurantDetailEntity
) {
    Card(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = restaurant.photograph)
                    .apply {
                        crossfade(true)
                        scale(Scale.FILL)
                    }.build()
            ),
            modifier = Modifier
                .width(300.dp)
                .height(250.dp),
            contentDescription = "Restaurant Poster Image"
        )
    }
}
