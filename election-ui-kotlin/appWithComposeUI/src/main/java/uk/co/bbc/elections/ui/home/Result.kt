package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Result(result: ResultUiState) = Row(
    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface),
    horizontalArrangement = Arrangement.SpaceBetween
) {
    val fontWeight = if(result.isWinner){
        FontWeight.ExtraBold
    }else{
        FontWeight.Normal
    }
    val candidateName = if(result.isWinner){
        "${result.name} (Winner)"
    }else{
        result.name
    }

    Text(
        text = result.party,
        color = MaterialTheme.colors.onSurface,
        fontWeight = fontWeight
    )
    Text(
        text = candidateName,
        color = MaterialTheme.colors.onSurface,
        fontWeight = fontWeight
    )
    Text(
        text = result.votes,
        color = MaterialTheme.colors.onSurface,
        fontWeight = fontWeight
    )
}

@Preview
@Composable
private fun ResultPreview() = Result(ResultUiState("Adder party", "1",
    "candidate 1", "1056", false))

@Preview
@Composable
private fun ResultWinnerPreview() = Result(ResultUiState("Adder party", "1",
    "candidate 1", "1094", true))
