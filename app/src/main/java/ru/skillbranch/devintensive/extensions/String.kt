package ru.skillbranch.devintensive.extensions

fun String.truncate(n : Int = 16): String?{
    val temp = this.trimEnd()
    if (temp.length <= n) return temp
    else return temp.subSequence(0, n).trimEnd().toString() + "..."
}

fun String.stripHtml(): String? {
    return this.replace(Regex("(</?[^>]+>)"), "")
               .replace("&nbsp;|&|\"|\'".toRegex(), "")
               .replace("[\\s]+".toRegex(), " ").trim()
}
