@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.memoapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.memoapp.model.Memo
import com.example.memoapp.model.memos
import com.example.memoapp.ui.theme.MemoAppTheme

@Composable
fun HomeScreen(homeState: HomeState) {
    MemoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //해결책 : remember에서 sorted 해준다
            //val memoList = remember { memos }
            val memoList = remember { memos.sortedBy { it.id }.toMutableStateList() }
            val onClickAction: (Int) -> Unit = {
                homeState.showContent(
                    it
                )
            }

            Column {
                AddMemo(memoList)
                MemoList(onClickAction, memoList)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemo(memoList: SnapshotStateList<Memo>) {
    val inputValue = remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .height(100.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextField(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            value = inputValue.value,
            onValueChange = { textFieldValue -> inputValue.value = textFieldValue }
        )
        Button(
            onClick = {
                memoList.add(
                    index = 0,
                    Memo(memoList.size, inputValue.value)
                )
                inputValue.value = ""
                count ++
            },
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
        ) {
            Text("ADD\n $count")
            //문제점 : composition scope (@composable)안에서 사용하는 값을 '쓰기' (recomposition 무한 루프 생김)
            //해결책 : 쓰지말자
            //count++
        }
    }
}


@Composable
fun ColumnScope.MemoList(onClickAction: (Int) -> Unit, memoList: SnapshotStateList<Memo>) {
    LazyColumn(
        modifier = Modifier
            .weight(1f)
    ) {

        items(
            //문제점: 모든 시점에 변화가 있지 않아도 매번 정렬.
           // memoList.sortedBy { it.id },
            memoList,
            key = { it.id }
        ) { memo ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.White)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                onClick = {
                    onClickAction(memo.id)
                }
            ) {
                Text(
                    text = memo.text,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}