package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{

    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}

const val YEAR = 31536000000L

fun Date.humanizeDiff(date:Date = Date()): String {
    var diff = 0L
    var tempDiff = 0L
    diff = this.time - date.time

    if (diff > 0) {

        when (diff) {
            in 0..SECOND -> return "только что"
            in SECOND..45 * SECOND -> return "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> return "через минуту"
            in 75 * SECOND..45 * MINUTE -> {
                tempDiff = diff / MINUTE
                if (tempDiff == 11L) return "через $tempDiff минут"
                else when (tempDiff % 10) {
                    1L -> return "через $tempDiff минуту"
                    in 2..4 -> return "через $tempDiff минуты"
                    else -> return "через $tempDiff минут"
                }
            }
            in 45 * MINUTE..75 * MINUTE -> return "через час"
            in 75 * MINUTE..22 * HOUR -> {
                tempDiff = diff / HOUR
                when (tempDiff) {
                    1L, 21L -> return "через $tempDiff час"
                    in 2..4, 22L -> return "через $tempDiff часа"
                    else -> return "через  $tempDiff часов"
                }
            }
            in 22 * HOUR..26 * HOUR -> return "через день"
            in 26 * HOUR..360 * DAY -> {
                tempDiff = diff / DAY
                if (tempDiff == 11L) return "через $tempDiff дней"
                else when (tempDiff % 10) {
                    1L -> return "через $tempDiff день"
                    in 2..4 -> return "через $tempDiff дня"
                    else -> return "через $tempDiff дней"
                }
            }
            else -> return "более чем через год"
        }
    }
    else {
        diff = abs(diff)
        when (diff) {
            in 0..SECOND -> return "только что"
            in SECOND..45 * SECOND -> return "несколько секунд назад"
            in 30 * SECOND..70 * SECOND -> return "минуту назад"
            in 70 * SECOND..50 * MINUTE -> {
                tempDiff = diff / MINUTE
                if (tempDiff == 11L) return "$tempDiff минут назад"
                else when (tempDiff % 10) {
                    1L -> return "$tempDiff минуту назад"
                    in 2..4 -> return "$tempDiff минуты назад"
                    else -> return "$tempDiff минут назад"
                }
            }
            in 50 * MINUTE..60 * MINUTE -> return "час назад"
            in 75 * MINUTE..22 * HOUR -> {
                tempDiff = diff / HOUR
                when (tempDiff) {
                    1L, 21L -> return "$tempDiff час назад"
                    in 2..4, 22L -> return "$tempDiff часа назад"
                    else -> return "$tempDiff часов назад"
                }
            }
            in 22 * HOUR..26 * HOUR -> return "день назад"
            in 26 * HOUR..360 * DAY -> {
                tempDiff = diff / DAY
                if (tempDiff == 11L) return "$tempDiff дней назад"
                else when (tempDiff % 10) {
                    1L -> return "$tempDiff день назад"
                    in 2..4 -> return "$tempDiff дня назад"
                    else -> return "$tempDiff дней назад"
                }
            }
            else -> return "более года назад"
        }
    }

}


enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value:Int): String {

        when(this){
            SECOND -> {
                if (value == 11) return "$value секунд"
                when(value % 10){
                    1 -> return "$value секунду"
                    in 2..4 -> return "$value секунды"
                    else -> return "$value секунд"
                }
            }
            MINUTE -> {
                if (value == 11) return "$value минут"
                when(value % 10){
                    1 -> return "$value минуту"
                    in 2..4 -> return "$value минуты"
                    else -> return "$value минут"
                }
            }
            HOUR -> {
                when(value % 10){
                    1 -> return "$value час"
                    in 2..4 -> return "$value часа"
                    else -> return "$value часов"
                }
            }
            DAY -> {
                if (value == 11) return "$value дней"
                when(value % 10){
                    1 -> return "$value день"
                    in 2..4 -> return "$value дня"
                    else -> return "$value дней"
                }
            }
        }
    }

}