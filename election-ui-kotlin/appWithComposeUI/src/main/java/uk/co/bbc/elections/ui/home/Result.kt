package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Result(result: ResultUiState) = Row(
    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface),
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = result.party,
        color = MaterialTheme.colors.onSurface
    )
    Text(
        text = result.name,
        color = MaterialTheme.colors.onSurface
    )
    Text(
        text = result.votes,
        color = MaterialTheme.colors.onSurface
    )
}

@Preview
@Composable
private fun ResultPreview() = Result(ResultUiState("Adder party", "1",
    "candidate 1", "1056"))
