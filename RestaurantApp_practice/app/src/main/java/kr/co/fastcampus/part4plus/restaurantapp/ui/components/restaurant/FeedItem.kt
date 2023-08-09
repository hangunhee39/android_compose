package kr.co.fastcampus.part4plus.restaurantapp.ui.components.restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import kr.co.fastcampus.part4plus.restaurantapp.R
import kr.co.fastcampus.part4plus.restaurantapp.features.common.entity.RestaurantFeedItemEntity
import kr.co.fastcampus.part4plus.restaurantapp.features.feed.presentation.input.IFeedViewModelInput
import kr.co.fastcampus.part4plus.restaurantapp.ui.theme.Paddings

private val CARD_WIDTH = 200.dp
private val ICON_SIZE = 12.dp

@Composable
fun FeedItem(
    feedItem: RestaurantFeedItemEntity,
    input: IFeedViewModelInput
) {
    Column(
        modifier = Modifier
            .width(CARD_WIDTH)
            .padding(Paddings.large)
    ) {
        Thumbnail(
            item = feedItem,
            input = input
        )

        Text(
            text = feedItem.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                top = Paddings.large
            ),
            style = MaterialTheme.typography.body2
        )

        Row(
            modifier = Modifier.padding(
                vertical = Paddings.medium
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(Paddings.small)
                    .size(ICON_SIZE),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_rating),
                tint = Color.Black.copy(
                    alpha = 0.5f
                ),
                contentDescription = "rating icon"
            )
            Text(
                text = String.format("%.2f",feedItem.rating),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = 0.5f
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Thumbnail(
    item: RestaurantFeedItemEntity,
    input: IFeedViewModelInput
) {
    Card(
        onClick = {
            input.openDetail(item.id)
        }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = item.photograph)
                    .apply {
                        crossfade(true)
                        scale(Scale.FILL)
                    }.build()
            ),
            modifier = Modifier
                .width(CARD_WIDTH)
                .height(120.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Restaurant Image"
        )
    }
}

