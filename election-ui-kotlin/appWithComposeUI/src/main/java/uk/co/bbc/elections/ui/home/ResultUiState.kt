package uk.co.bbc.elections.ui.home

data class ResultUiState(
    val party: String,
    val id: String,
    val name: String,
    val votes: String,
    val isWinner: Boolean,
)
