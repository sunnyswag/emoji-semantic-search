package com.example.emojisemanticsearch

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.emojisemanticsearch.entity.EmojiEntity
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
                        DisplayEmoji(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayEmoji(modifier: Modifier = Modifier) {
    val emojiItems = listOf(
        EmojiEntity("🐶", "Dog"),
        EmojiEntity("🐱", "Cat"),
        EmojiEntity("🐭", "Mouse"),
        EmojiEntity("🐹", "Hamster"),
        EmojiEntity("🐰", "Rabbit"),
        EmojiEntity("🦊", "Fox"),
        EmojiEntity("🐻", "Bear"),
        EmojiEntity("🐼", "Panda"),
        EmojiEntity("🐻‍❄️", "Polar Bear"),
        EmojiEntity("🐶", "Dog"),
        EmojiEntity("🐱", "Cat"),
        EmojiEntity("🐭", "Mouse"),
        EmojiEntity("🐹", "Hamster"),
        EmojiEntity("🐰", "Rabbit"),
        EmojiEntity("🦊", "Fox"),
        EmojiEntity("🐻", "Bear"),
        EmojiEntity("🐼", "Panda"),
        EmojiEntity("🐻‍❄️", "Polar Bear"),
        EmojiEntity("🐶", "Dog"),
        EmojiEntity("🐱", "Cat"),
        EmojiEntity("🐭", "Mouse"),
        EmojiEntity("🐹", "Hamster"),
        EmojiEntity("🐰", "Rabbit"),
        EmojiEntity("🦊", "Fox"),
        EmojiEntity("🐻", "Bear"),
        EmojiEntity("🐼", "Panda"),
        EmojiEntity("🐻‍❄️", "Polar Bear")
    )

    LazyColumn(modifier = modifier) {
        items(emojiItems.size) { index ->
            EmojiItem(emojiItems[index])
        }
    }
}

@Composable
fun EmojiItem(emojiEntity: EmojiEntity) {
    val context = LocalContext.current
    Row (modifier = Modifier.clickable {
        saveToClipboard(context, emojiEntity.emoji)
        Toast.makeText(
            context,
            "Copied ${emojiEntity.emoji} to clipboard",
            Toast.LENGTH_SHORT
        ).show()
    }) {
        Text(
            text = emojiEntity.emoji,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = emojiEntity.name,
            modifier = Modifier.padding(10.dp)
        )
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