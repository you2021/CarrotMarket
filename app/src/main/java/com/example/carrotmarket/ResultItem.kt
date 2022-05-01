package com.example.carrotmarket

// 나중에 응답값으로 Intent로 해당 class 넘겨야 되면
// Parcelable 사용
// Parcelable : 데이터 직렬화 할때 필요한 interface, Parcelable 을 implement 시키면 거기 안에 멤버 메소드 전부 구현해야하는데 이거 어려우니까
// Pacelize 사용 - 알아서 다해줌
// Google 에 찾아보고 나중에 Gradle 에 추가 해서 쓰면 됨

data class ResultItem(
    val status : String,
    val code : String,
    val cookie : String,
)
