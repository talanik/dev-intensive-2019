package ru.skillbranch.devintensive.extensions

fun String.truncate(strindSize : Int = 16): String?{
    val txt: String = this
    if (txt.length > strindSize) return "${txt.substring(0, strindSize+1).trimEnd()}..."
    else return txt
}

fun String.stripHtml(): String? {
    return this.replace(Regex("(</?[^>]+>)"), "")
               .replace("&nbsp;|&|\"|\'".toRegex(), "")
               .replace("[\\s]+".toRegex(), " ").trim()
}