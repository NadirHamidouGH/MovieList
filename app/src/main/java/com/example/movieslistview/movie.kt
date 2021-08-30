package com.example.movieslistview

class movie {
    var id = 0
    var title: String? = null
    var date: String? = null
    var note: String? = null
    var description: String? = null
    var imgUrl: String? = null

    constructor() {}
    constructor(title: String?, date: String?, description: String?, imgUrl: String?) {
        this.title = title
        this.date = date
        this.description = description
        this.imgUrl = imgUrl
    }
}