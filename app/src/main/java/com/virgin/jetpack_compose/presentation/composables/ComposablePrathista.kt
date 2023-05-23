package com.virgin.jetpack_compose.domain

import PrathistaList
import PrathistaListItem
import android.util.Log
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.virgin.jetpack_compose.R
import com.virgin.jetpack_compose.data.model.NetworkState
import com.virgin.jetpack_compose.presentation.viewmodel.PrathistaViewModel

@Composable
fun PrathistaView() {
    val viewModel = viewModel<PrathistaViewModel>()
    LaunchedEffect(key1 = true) {
        viewModel.getPrathista()
    }
    var prathistaList = PrathistaList()
    // get updated categories from view model
    val prathistaState = viewModel.prathistaState.collectAsStateWithLifecycle()
    when (prathistaState.value) {
        is NetworkState.Initial -> {
            Log.e("flow:", "flow : Initial")
        }
        is NetworkState.Loading -> {
            Log.e("flow:", "flow : Loading")

        }
        is NetworkState.Error -> {
            Log.e("flow:", "flow : Error")

        }
        is NetworkState.Success<*> -> {
            // converting response to model class object
            prathistaList = (prathistaState.value as NetworkState.Success<*>)._data as PrathistaList
            Log.e("flow:", "flow : Success $prathistaList")
        }
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                contentPadding = innerPadding)
//                modifier = Modifier.animateItemPlacement()
                {
                    items(
                        prathistaList,
                        key = { item -> item.PM_Code.toString() }) { prathista ->
                        // override the onRemoveItemClick value here
                        ItemRow(prathista, onRemoveItemClick = {})
                    }
                }

        }
        if (prathistaList.isEmpty()) {
            loadProgressbar()
        }
    }

}


@Composable
fun ItemRow(
    category: PrathistaListItem,
    onRemoveItemClick: (category: PrathistaListItem) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cricket_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                // clip is used to provide style to the image like curved corner etc.
                .clip(MaterialTheme.shapes.small)
        )
        Column {
            Text(
                text = category.PM_NameEng.toString(),
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
            Text(
                text = category.PM_Code.toString(),
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )

        }
        
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { onRemoveItemClick(category) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrathistaPreview() {
    PrathistaView()
}
