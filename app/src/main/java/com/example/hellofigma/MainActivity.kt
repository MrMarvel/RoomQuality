/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hellofigma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hellofigma.data.SharedViewModel
import com.example.hellofigma.monitoringitembuildingnew.MonitoringItemBuildingNew
import com.example.hellofigma.screens.MonitoringScreen
import com.example.hellofigma.ui.theme.HelloFigmaTheme

val elem = @Composable {
    MonitoringItemBuildingNew(
        dateNumber = "19",
        dateFull = "Март 19 2024",
        coordinates = "55.685812, 37.409567",
        projectName = "ЖК Жульен"
    )
}

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloFigmaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MonitoringScreen(sharedViewModel = sharedViewModel)
                }
            }
        }
    }
}

@Preview(heightDp = 360)
@Composable
private fun PreviewThreeBlock() {
    HelloFigmaTheme() {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(Modifier.wrapContentSize()) {
                elem()
                elem()
            }



        }
    }
}

@Preview
@Composable
fun MonitoringBuildingItemPreview() {
    Box(Modifier.wrapContentSize()) {
        HelloFigmaTheme() {
            MonitoringItemBuildingNew(
                dateNumber = "19",
                dateFull = "Март 19 2024",
                coordinates = "55.685812, 37.409567",
                projectName = "ЖК Жульен в подольске"
            )
        }
    }
}