package com.cis.kotlinactionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var searchView: SearchView? = null
    var listData = arrayOf("aaa","bbb","ccc","ddd","eee")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData)
        mainLv.adapter = adapter

        // 리스트뷰의 검색기능 활성화
        mainLv.isTextFilterEnabled = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val item = menu?.findItem(R.id.item1)

        val listener = ActionListener()

        item?.setOnActionExpandListener(listener)

        searchView = item?.actionView as SearchView
        searchView?.queryHint = "입력해주세요."

        val listener2 = ActionListener2()
        searchView?.setOnQueryTextListener(listener2)

        return true
    }

    inner class ActionListener: MenuItem.OnActionExpandListener {
        // 펼쳐졌을 때 호출됨
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
            tv.text = "펼쳐졌습니다."

            return true
        }

        // 접혔을 때 호출됨
        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            tv.text = "접혀졌습니다."

            return true
        }
    }

    inner class ActionListener2: SearchView.OnQueryTextListener {
        // 확인버튼을 눌렀을 때 호출됨
        override fun onQueryTextSubmit(query: String?): Boolean {
            tv2.text = "확인버튼 눌림 : ${query}"
            searchView?.clearFocus()
            return true
        }

        // 입력할 때 호출됨
        override fun onQueryTextChange(newText: String?): Boolean {
            tv2.text = newText

            mainLv.setFilterText(newText)

            // 검색란이 다 지워졌다면 아무 내용도 안나타나도록 함
           if(newText?.length == 0) {
                mainLv.clearTextFilter()
            }

            return true
        }

    }
}
