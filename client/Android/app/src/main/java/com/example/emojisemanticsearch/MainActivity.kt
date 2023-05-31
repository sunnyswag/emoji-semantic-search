package com.example.emojisemanticsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emojisemanticsearch.ui.theme.EmojiSemanticSearchTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmojiSemanticSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column (modifier = Modifier.fillMaxSize()) {
                        val systemUiController = rememberSystemUiController()
                        val useDarkIcons = MaterialTheme.colors.isLight

                        SideEffect {
                            systemUiController.setSystemBarsColor(
                                color = Color.Transparent,
                                darkIcons = useDarkIcons
                            )
                        }

                        SearchEmoji(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchEmoji(modifier: Modifier) {
    var searchText by remember { mutableStateOf(TextFieldValue(""))}

    TextField(
        value = searchText,
        placeholder = { Text("Find the most relevant emojis") },
        onValueChange = { searchText = it },
        modifier = modifier.padding(10.dp)
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            scope.launch {
                saveToClipboard(context, "Hello $name!")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmojiSemanticSearchTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun SearchEmojiPreview() {
    EmojiSemanticSearchTheme {
        SearchEmoji(modifier = Modifier.fillMaxWidth())
    }
}