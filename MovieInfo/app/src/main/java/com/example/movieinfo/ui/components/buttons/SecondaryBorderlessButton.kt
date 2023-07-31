import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.unit.dp
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieinfo.ui.components.buttons.PrimaryButton
import com.example.movieinfo.ui.theme.MovieInfoTheme
import com.example.movieinfo.ui.theme.Paddings

@Composable
fun SecondaryBorderlessButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = null
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(Paddings.small)
            )
        }
    }
}

@Preview
@Composable
fun SecondaryBoradlessButtonPreview(){
    MovieInfoTheme {
        SecondaryBorderlessButton(
            text = "summit"
        ) {

        }
    }
}