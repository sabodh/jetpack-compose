package com.virgin.jetpack_compose.domain

import VCategory
import VCategoryItem
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.virgin.jetpack_compose.R
import com.virgin.jetpack_compose.data.model.NetworkState
import com.virgin.jetpack_compose.presentation.viewmodel.CategoryViewModel

@Composable
fun CategoryList() {
    val viewModel = viewModel<CategoryViewModel>()
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(LazyFormEvent.LoadCategory)
    }
    var categoryList: VCategory
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycleAware = remember(viewModel.categoryState, lifecycleOwner) {
        viewModel.categoryState.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    // get updated categories from view model
    val vCategories = viewModel.categories.collectAsStateWithLifecycle()

    val networkState: NetworkState by flowLifecycleAware
        .collectAsState(initial = NetworkState.Initial)
    when (networkState) {
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
            categoryList = (networkState as NetworkState.Success<*>)._data as VCategory
            viewModel.onEvent(LazyFormEvent.UpdateCategory(categoryList))
            Log.e("flow:", "flow : Success $categoryList")
        }
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                contentPadding = innerPadding
//                modifier = Modifier.animateItemPlacement()
            ) {
                items(
                    vCategories.value.vCategories,
                    key = { item -> item.VC_Code.toString() }) { category ->
                    // override the onRemoveItemClick value here
                    ItemRow(category, onRemoveItemClick = {
                        viewModel.onEvent(LazyFormEvent.ItemRemoved(category))
                    })
                }
            }
        }
        if (vCategories.value.vCategories.isEmpty()) {
            loadProgressbar()
        }
    }

}


@Composable
fun loadProgressbar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ItemRow(
    category: VCategoryItem,
    onRemoveItemClick: (category: VCategoryItem) -> Unit
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
                text = category.VC_Name.toString(),
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
            Text(
                text = category.VC_Code.toString(),
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
fun ListPreview() {
    CategoryList()
}
