package problem3


import java.time.LocalDate
import java.time.Month
import java.time.MonthDay
import java.time.Year
import java.util.*
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities


data class Date(val year: Int = LocalDate.now().year, val month: Int = LocalDate.now().monthValue, val day:Int = LocalDate.now().dayOfMonth):Comparable<Date>{
    companion object{
        fun Date.isLeapYear(): Boolean {
            var leap:Boolean = false
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    leap = year % 400 == 0
                } else
                    leap = true
            } else
                leap = false
            return leap
        }
        fun Date.isValid():Boolean {
            val longs = listOf<Int>(1,3,5,7,8,10,12)
            if (year>0){
                if (longs.contains(month) && day<=31 && day>=1){
                    return true
                }else if (this.isLeapYear() && month>=1 && month<=12 && day<=28 && day>=1){
                    return true
                }else if (month>=1 && month<=12 && day<=30 && day>=1){
                    return true
                }
            }
            return false
        }
    }

    override fun compareTo(other: Date): Int {
        if (year == other.year){
            if (month == other.month){
                return this.day - other.day
            }
            return this.month - other.month
        }
        return this.year - other.year
    }

}
