package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import uk.co.bbc.elections.R

@Composable
fun Result(result: ResultUiState) {
    val contentDescription1 = if(result.isWinner){
        stringResource(id = R.string.winner_candidate_results,
            result.name, result.party, result.votes)
    }else{
        stringResource(id = R.string.candidate_results, result.name, result.party, result.votes)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .semantics(mergeDescendants = true) {
                //stateDescription = "Sample text"
            }
            .clearAndSetSemantics {
                contentDescription = contentDescription1
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val textSemantics = Modifier.semantics { stateDescription = "" }
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
            fontWeight = fontWeight,
            modifier = textSemantics
        )
        Text(
            text = candidateName,
            color = MaterialTheme.colors.onSurface,
            fontWeight = fontWeight,
            modifier = textSemantics
        )
        Text(
            text = result.votes,
            color = MaterialTheme.colors.onSurface,
            fontWeight = fontWeight,
            modifier = textSemantics
        )
    }

}

@Preview
@Composable
private fun ResultPreview() = Result(ResultUiState("Adder party", "1",
    "candidate 1", "1056", false))

@Preview
@Composable
private fun ResultWinnerPreview() = Result(ResultUiState("Adder party", "1",
    "candidate 1", "1094", true))
