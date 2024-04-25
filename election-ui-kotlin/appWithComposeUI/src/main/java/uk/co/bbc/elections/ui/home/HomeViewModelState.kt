package uk.co.bbc.elections.ui.home

import uk.co.bbc.elections.api.Candidate
import uk.co.bbc.elections.api.Result

data class HomeViewModelState(
    val results: List<Result> = emptyList(),
    val candidates: Map<Int, Candidate> = emptyMap(),
    val countingComplete: Boolean = false,
    val loading: Boolean = false
) {

    fun toUiState() = HomeUiState(
        results = results.sortedByDescending { it.votes }.map { result ->
            ResultUiState(
                result.party,
                result.candidateId.toString(),
                candidates[result.candidateId]?.name?:"",
                result.votes.toString(),
                countingComplete && result.votes == results.maxOf { it.votes }
            )
        },
        loading = loading,
        isComplete = countingComplete
    )
}
