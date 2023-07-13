package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

class TodoViewModel : ViewModel() {
    //remember은 못 쓴다 , by로 value 바로 밭기는 가능
    //remember는 composable 함수의 생명주기에 맞추기 때문에 viewModel에서 못쓴다
    //val text = mutableStateOf("")

    //observeAsState로 연결
    private val _text = MutableLiveData("")
    val text : LiveData<String> = _text     //외부에서 직접 바꿀수 없게

    val setText: (String) -> Unit = {
        _text.value = it
    }

    //mutableStateLiseOf - 추가, 삭제, 대입 -> ui가 갱신,
    //      각 항복의 필드가 바뀌었을 때 -> 갱신 안되는 문제 (immutable 한 객체를 넣으면 해결)
    //LiveData<List<x>>.observeAsState() - List가 통채로 다른 List로 바뀌었을 때만 State가 갱신 (비추)

//    val toDoList = mutableStateListOf<ToDoData>()
    private val _rawToDoList = mutableListOf<ToDoData>()
    private val _toDoList = MutableLiveData<List<ToDoData>>()
    val toDoList: LiveData<List<ToDoData>> = _toDoList

    val onSubmit: (String) -> Unit = {
        val key = (_rawToDoList.lastOrNull()?.key ?: 0) + 1
        _rawToDoList.add(ToDoData(key, it))
        _toDoList.value = _rawToDoList.toMutableList()
        _text.value = ""
    }

    //List 만드는 3가지 방법 (결과는 같음)
    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawToDoList.indexOfFirst {
            it.key == key
        }
        _rawToDoList[i] = _rawToDoList[i].copy(done = checked)
        //그냥 done 만 바꾸면 ui가 새로고침 안됨 (todo 자체를 바꿈)
        _toDoList.value =  ArrayList(_rawToDoList)
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList.removeAt(i)
        _toDoList.value =  _rawToDoList.toMutableList()
    }

    val onEdit: (Int, String) -> Unit = { key, text ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(text = text)
        _toDoList.value = mutableListOf<ToDoData>().also {
            it.addAll(_rawToDoList)
            //shallow copy 문제 생길 수도..
        }
    }
}

//immutable 객체 여서 내용이 바뀐다고 ui가 안바뀜
//안에 객체를 mutable 하게 할 순 있지만 이 방버이 더 편리
data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)

//import androidx.lifecycle.viewmodel.compose.viewModel 를 import
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(viewModel: TodoViewModel = viewModel()) {

    Scaffold {
        Column {
            ToDoInput(
                viewModel.text.observeAsState("").value,
                viewModel.setText,
                viewModel.onSubmit
            )
            val items =viewModel.toDoList.observeAsState(emptyList()).value
            LazyColumn {
                //key 를 설정해줘야 compose가 잘 작동한다
                items(items = items, key = { it.key }) {
                    ToDo(
                        toDoData = it,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete,
                        onEdit = viewModel.onEdit
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            maxLines = 1,
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    MyApplicationTheme {
        ToDoInput("테스트", {}, {})
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Crossfade(targetState = isEditing) {
            when (it) {
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    )
                    {
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = "완료")
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = {b->
                                onToggle(toDoData.key, b)
                            })
                        Button(onClick = {
                            isEditing = true
                        }) {
                            Text(text = "수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))

                        Button(onClick = {
                            onDelete(toDoData.key)
                        }) {
                            Text(text = "삭제")

                        }
                    }
                }

                true -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    )
                    {
                        val (newText, setNewText) = remember {
                            mutableStateOf(toDoData.text)
                        }

                        OutlinedTextField(
                            value = newText,
                            onValueChange = setNewText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = {
                            onEdit(toDoData.key, newText)
                            isEditing = false
                        }) {
                            Text(text = "완료")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    MyApplicationTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting()
    }
}