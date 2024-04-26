package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.bbc.elections.R

@Composable
fun ResultHeader() = Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primary).padding(4.dp),
    horizontalArrangement = Arrangement.SpaceBetween) {
    Text(
        text = stringResource(id = R.string.results_header_party).uppercase()
    )
    Text(
        text = stringResource(id = R.string.results_header_candidate).uppercase()
    )
    Text(
        text = stringResource(id = R.string.results_header_votes).uppercase()
    )
}

@Preview
@Composable
private fun ResultHeaderPreview() = ResultHeader()
