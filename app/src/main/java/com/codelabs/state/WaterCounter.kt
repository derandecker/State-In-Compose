/*
 * NOTICE - file modified by https://www.github.com/derandecker
 *
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codelabs.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
//    Column(modifier = modifier.padding(16.dp)) {
//        var count = 0
//        Text("You've had $count glasses.")
//        Button(onClick = { count++ }, Modifier.padding(top = 8.dp)) {
//            Text("Add one")
//        }
//    }

//    Column(modifier = modifier.padding(16.dp)) {
//        Column(modifier = modifier.padding(16.dp)) {
//            var count by remember { mutableStateOf(0) }
//
//            Text("You've had $count glasses.")
//            Button(onClick = { count++ }, Modifier.padding(top = 8.dp)) {
//                Text("Add one")
//            }
//        }
//    }

//    var count by rememberSaveable { mutableStateOf(0) }
//
//    Column(modifier = modifier.padding(16.dp)) {
//        if (count > 0) {
//            // This text is present if the button has been clicked
//            // at least once; absent otherwise
//            Text("You've had $count glasses.")
//        }
//            Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
//                Text("Add one")
//            }
//    }
    Column(modifier = Modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?",
                    onCheckedChange = {},
                    checked = false
                )
            }
            Text(
                text = pluralStringResource(R.plurals.num_of_glasses, count, count),
                fontSize = if (count > 9) {
                    32.sp
                } else {
                    12.sp
                },
                fontWeight = if (count > 9) {
                    FontWeight.Black
                } else {
                    FontWeight.Light
                }
            )
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp)
            ) {
                Text("Clear water count")
            }
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    StatelessCounter(count = count, onIncrement = { count++ })
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}