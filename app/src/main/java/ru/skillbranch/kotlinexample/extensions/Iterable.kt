package ru.skillbranch.kotlinexample.extensions

fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T>{
    var result = mutableListOf<T>()
    loop@ for(item in this){
        if(predicate(item)) break@loop
        result.add(item)
    }
    return  result.toList()
}