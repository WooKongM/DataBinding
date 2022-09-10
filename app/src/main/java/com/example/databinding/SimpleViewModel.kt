package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SimpleViewModel :ViewModel() {
    // Live data : get/set  변경가능한 라이브데이터
    private val _name = MutableLiveData("James")
    private val _lastName = MutableLiveData("Bond")
    private val _likes = MutableLiveData(0)
    // Read only Live data : get. 읽기만 가능. 뷰에 바인딩 되어있음.
    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes

    val popularity: LiveData<Popularity> = Transformations.map(_likes) {
        when {
            it > 9 -> Popularity.STAR
            it > 4 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }

    fun onLike() {
        _likes.value = (_likes.value ?: 0) + 1
    }

    /* //Static data
    val name = "James"
    val lastName = "Bond"
    var likes = 0
        private  set //setLikes()를 만들지 않음. -> 외부에서 likes변경을 할 수 없음. 읽기는 가능
    val popularity: Popularity
        get() {
            return when {
                likes > 9 -> Popularity.STAR
                likes > 4 -> Popularity.POPULAR
                else -> Popularity.NORMAL
            }
        }
    fun onLike() { //숫자 변화를 일으킴
        likes++
    }
    */

}

enum class Popularity { //열거형 클래스
    NORMAL, POPULAR, STAR
}