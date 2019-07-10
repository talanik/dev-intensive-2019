package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?):Pair<String?, String?>{
        val parts: List<String>? = fullName?.trim()?.replace("\\s+".toRegex(), " ")?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(if (firstName.isNullOrBlank()) null else firstName, if (lastName.isNullOrBlank()) null else lastName)
    }

    operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

    fun transliteration(payload: String, divider: String = " "):String {
        val map = mapOf("а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya", "А" to "A", "Б" to "B", "В" to "V", "Г" to "G", "Д" to "D", "Е" to "E", "Ё" to "E", "Ж" to "Zh", "З" to "Z", "И" to "I", "Й" to "I", "К" to "K", "Л" to "L", "М" to "M", "Н" to "N", "О" to "O", "П" to "P", "Р" to "R", "С" to "S", "Т" to "T", "У" to "U", "Ф" to "F", "Х" to "H", "Ц" to "C", "Ч" to "Ch", "Ш" to "Sh", "Щ" to "Sh'", "Ъ" to "", "Ы" to "I", "Ь" to "", "Э" to "E", "Ю" to "Yu", "Я" to "Ya")
        return payload.replace(Regex("[${payload}]")){
            when(it.value) {
                in Regex("[\\w]")-> it.value
                in Regex("[а-я,А-Я]")-> map.getValue(it.value)
                else -> divider
            }
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var txt: String? = ""

        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) txt = null
        else if (firstName.isNullOrBlank()) txt = lastName?.substring(0,1)?.toUpperCase()
        else if (lastName.isNullOrBlank()) txt = firstName?.substring(0,1)?.toUpperCase()
        else txt = "${firstName?.substring(0,1)?.toUpperCase()}${lastName?.substring(0,1)?.toUpperCase()}"

        return txt
    }
}
