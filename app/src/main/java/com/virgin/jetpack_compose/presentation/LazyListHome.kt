package com.virgin.jetpack_compose.presentation

import VCategory
import VCategoryItem
import android.util.Log
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.virgin.jetpack_compose.R
import com.virgin.jetpack_compose.model.NetworkState
import com.virgin.jetpack_compose.viewmodel.CategoryViewModel

@Composable
fun CategoryList() {
    val viewModel = viewModel<CategoryViewModel>()
    LaunchedEffect(key1 = true) {
        viewModel.getCategory()
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
    val vCategories = viewModel.categories.collectAsState()

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
            viewModel.updateCategories(categoryList)
            Log.e("flow:", "flow : Success $categoryList")
        }
    }

    Scaffold { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(vCategories.value as List<VCategoryItem>) { category ->
                ItemRow(category)
            }
        }
    }
}

@Composable
fun ItemRow(
    category: VCategoryItem
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null)
        Column {
            Text(
                text = category.VC_Name.toString(),
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
            Text(
                text = category.VC_NameEng.toString(),
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    CategoryList()
}
