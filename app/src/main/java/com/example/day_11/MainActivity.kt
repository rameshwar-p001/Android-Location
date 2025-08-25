package com.example.day_11

import android.Manifest
import android.R.attr.permission
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.day_11.ui.theme.Day_11Theme
import androidx.lifecycle.viewmodel.compose.viewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LocationViewModel = viewModel()
            Day_11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        MyApp(viewModel)
                    }
                }
            }
        }
    }
}

//Parent Composable
@Composable
fun MyApp(viewModel: LocationViewModel) {
    // ✅ ab yaha sahi jagah hai
    val context = LocalContext.current
    val locationUtills = LocationUtills(context)

    LocationDisplay(locationUtills = locationUtills, context = context, viewModel = viewModel)
}

@Composable
fun LocationDisplay(locationUtills: LocationUtills, context: Context, viewModel: LocationViewModel) {
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            ) {
                // I have location to access
            } else {
                // Ask for permission
                val rationalRequired =
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        context as MainActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(
                                context,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )

                if (rationalRequired) {
                    Toast.makeText(
                        context,
                        "For this Feature you want location permission ",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "For this Feature you want enable the permission from setting ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "location not available ")

        Button(onClick = {
            if (locationUtills.hasLocationPermission(context)) {
                // Permission already granted → update the location
            } else {
                // Request Location permission
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text(text = " Get Location...! ")
        }
    }
}
