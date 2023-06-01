package com.example.emojisemanticsearch

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emojisemanticsearch.ui.theme.EmojiSemanticSearchTheme

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
    val context = LocalContext.current

    TextField(
        value = searchText,
        placeholder = { Text("Find the most relevant emojis") },
        onValueChange = { searchText = it },
        modifier = modifier.padding(10.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                Toast.makeText(
                    context,
                    "Search for ${searchText.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchEmojiPreview() {
    EmojiSemanticSearchTheme {
        SearchEmoji(modifier = Modifier.fillMaxWidth())
    }
}