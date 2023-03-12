package com.virgin.jetpack_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// list view row item
@Composable
fun ListViewItem(member: TeamMembers) {


    // divided the cell as two parts
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // image logo
        ImageSession()
        // team member details
        ColumText(member = member)
    }

}

@Composable
fun ImageSession() {
    Surface(
        modifier = Modifier
            .height(80.dp)
            .width(80.dp)
            .background(Color.White)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null)

    }
}

@Composable
fun ColumText(member: TeamMembers) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "Name: ${member.name}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Text(
            text = "Age: ${member.age}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Magenta,
            fontSize = 16.sp
        )
        Text(
            text = "Team: ${member.team}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp),
            fontWeight = FontWeight.SemiBold,
            color = Color.Magenta,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ListHeader(header: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        Text(
            text = "${header}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 15.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }
}