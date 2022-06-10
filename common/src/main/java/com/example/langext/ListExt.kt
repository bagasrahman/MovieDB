package com.example.langext

fun <T> ArrayList<T>.toggle(t:T){
    if(this.contains(t)){
        this.remove(t)
    } else {
        this.add(t)
    }
}