package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.bbc.elections.R

@Composable
fun Home(viewModel: HomeViewModel, homeAndroidViewModel: HomeAndroidViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val accessebilityState by homeAndroidViewModel.talkbackState.collectAsState()
    val networkState by homeAndroidViewModel.networkState.collectAsState()
    val (showResultComplete, setShowResultComplete) = remember { mutableStateOf(false) }
    val (showNetworkConnection, setShowNetworkConnection) = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = uiState.isComplete){
        setShowResultComplete(uiState.isComplete)
    }
    LaunchedEffect(key1 = networkState){
        setShowNetworkConnection(!networkState)
    }
    if(showResultComplete){
        InfoAlertDialog(stringResource(id = R.string.counting_complete)){
            setShowResultComplete(false)
        }
    }
    if(showNetworkConnection){
        InfoAlertDialog(stringResource(id = R.string.no_network_message)){
            setShowNetworkConnection(false)
        }
    }
    Home(uiState, accessebilityState) { viewModel.refresh() }
}

@Composable
fun Home(uiState: HomeUiState, accessibilityState: Boolean = false, refresh: () -> Unit) = Scaffold(
    floatingActionButton = {
        if(!uiState.isComplete){
            FloatingActionButton(onClick = { if (!uiState.loading) refresh() }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = stringResource(id = R.string.refresh)
                )
            }
        }
    }
) { innerPadding ->
    LazyColumn(
        Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        if(!accessibilityState){
            item { ResultHeader() }
        }
        items(uiState.results) { Result(it) }
    }
}

@Preview
@Composable
private fun HomePreview() = Home(
    HomeUiState(
        results = listOf(
            ResultUiState("Adder party", "1", "Candidate 1", "1056", true),
            ResultUiState("b", "2","Candidate 2", "100", false)
        ),
        loading = false,
        isComplete = false
    )
) {}


@Preview
@Composable
fun InfoAlertDialog(text: String = "Some text", onOkClick: (() -> Unit)? = null){
    AlertDialog(onDismissRequest = { /*TODO*/ },
        title = {Text(text)},
        confirmButton = {
            TextButton(onClick = {onOkClick?.invoke()}) {
                Text(stringResource(id = R.string.dialog_ok))
            }
        })
}