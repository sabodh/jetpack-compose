import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExposedDropdownMenu(categoryList: VCategory = VCategory()) {
//    val items = listOf("Item 1", "Item 2", "Item 3")
    var selectedItem by remember { mutableStateOf(VCategoryItem()) }
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = "Selected item: $selectedItem",
            modifier = Modifier.padding(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray)
                .clickable {
                    expanded = !expanded
                }
        ) {


            Text(
                text = selectedItem.VC_NameEng.toString(),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1
            )




            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categoryList.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = item
                            expanded = false
                        }
                    ) {
                        Text(text = item.VC_NameEng.toString(),
                            modifier = Modifier.
                            fillMaxWidth()
                                .animateContentSize()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrathistaPreview() {
    ExposedDropdownMenu()
}
