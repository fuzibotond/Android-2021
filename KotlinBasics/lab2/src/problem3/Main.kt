package problem3

import problem3.Date.Companion.isLeapYear
import problem3.Date.Companion.isValid
import kotlin.random.Random

fun main(){
    val date = Date()
    println(date)
    println(date.isLeapYear())
    println(Date(2000, 13, 12).isValid())
    val randomDates = mutableListOf<Date>()
    while (randomDates.size <= 10){
        val temp = Date(Random.nextInt(-100,3000), Random.nextInt(-100, 300),Random.nextInt(-100, 300))
        if (temp.isValid()){
            randomDates.add(temp)
        }
    }
    randomDates.forEach { println(it) }
    randomDates.sort()
    println("Sorted: ")
    randomDates.forEach { println( it) }
    println("Reversed sorted list: ")
    randomDates.reverse()
    randomDates.forEach { println(it) }
    println("Sorted with custom comparator: ")
    val myComparator = Comparator { date1:Date, date2:Date -> date1.day - date2.day  }
    randomDates.sortedWith(myComparator).forEach { println(it) }


}