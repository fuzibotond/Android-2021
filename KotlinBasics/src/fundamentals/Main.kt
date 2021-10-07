package fundamentals

import kotlin.random.Random


fun main(){
    val a = 2
    val b = 3
    println("$a+$b=${a+b}")
    // 2.

    val daysOfWeek: List<String> = listOf("monday", "tuesday", "wednesday","thursday","friday","saturday","sunday")
//    for(day in daysOfWeek){
//        print("$day ")
//    }
    println("Days of the Week: ")
    daysOfWeek.forEach{
            day -> print(day + " ")
    }
    println()

    println("Starts with 'T' : ")
    daysOfWeek
        .filter { it.startsWith("T") }
        .forEach { println(it) }

    println("Contains 'e' :")
    daysOfWeek
        .filter { it.contains('e') }
        .forEach { println(it) }

    println("All days with lengths of 6 char: ")
    daysOfWeek
        .filter { it.length == 6 }
        .forEach { println(it) }

    //3.
    println("Prime numbers till 100: ")
    for (number in 1..100){
        if (primeChecker(number.toShort())){
            print(number.toString() + " ")
        }
    }

    //4. Higher Order Fuctions
    println()
    println("Encode: " + encode("Boti"))
    println("Decode: " + decode(encode("Boti")))
    var lmbdEncode = {
        msg: String -> String(msg.map { it+4 }.toCharArray())
//        msg:String -> String(msg.encodeToByteArray())
    }
    var lmbdDecode = {
            msg: String -> String(msg.map { it-4 }.toCharArray())
    }
    println("Encoded message with higher order function: " + messageCoding("Boti", lmbdEncode))
    println("Decoded message with higher order function: " + messageCoding(messageCoding("Boti", lmbdEncode), lmbdDecode))
//  5. Compact functions
    val numbers = (1.. 10).toList()
    evensFromList(numbers)

//  6. Map
    println()
    println("Doubled values: ")
    numbers.map { print(" ".plus(it*2) )}
    println()
    println("Capitlalized names of week: ")
    daysOfWeek.map{print(String(it.map { it.toUpperCase() }.toCharArray()) + " ")}
    println()
    println("First Letter capitalized only: ")
    daysOfWeek.map{ print(it.capitalize() + " ")}
    println()
    println("Length(character) of the days: ")
    daysOfWeek.map { print(it + ": " + it.length+" ") }
    println()
    println("The average of lengths: ")
    var sum = 0;
    daysOfWeek.map { sum+=it.length }
    print((sum.toDouble()/daysOfWeek.size))
//  7.Mutable/immutable
    println()
    var mutableList = daysOfWeek.toMutableList()
    for (day in daysOfWeek){
        if (day.contains('n')){
            mutableList.remove(day)
        }

    }
    for ((index, value ) in mutableList.withIndex()){
        println("Item at " + index +" is " + value)
    }

    mutableList.sortBy { it[0] }
    mutableList.forEach { print(it+" ") }

//    8.

    println()
    println("Random numbers: ")
    val randomValues = List(10) { Random.nextInt(0, 100) }
    randomValues.forEach { println(it ) }
    println()
    println("Sorted: ")
    var mutableRandoms = randomValues.toMutableList()
    mutableRandoms.sort()
    mutableRandoms.forEach { println(it ) }
    println()
    println("Contains even: ")
    println(randomValues.any { it % 2 != 0 })

    println("All numbers are even: ")
    println(randomValues.all { it % 2 != 0 })

//    var average = randomValues.average()
//    println("Average: " + average)
    var sum2 = 0.0
    randomValues.forEach { sum2+=it }
    println("Average2: " + sum2/randomValues.size)
}

fun primeChecker(number: Short):Boolean{
    var i = 2
    var flag = false
    while (i <= number / 2 ){
        if (number % i == 0){
            flag = true
            break
        }
        i++
    }
    return !flag
}
fun encode(text:String): String {
    var encodedList = text.map { it + 3 }
    val string = String(encodedList.toCharArray())
    return  string
}
fun decode(text:String): String {
    var encodedList = text.map { it - 3 }
    val string = String(encodedList.toCharArray())
    return  string
}
fun messageCoding(msg: String, func: (String) -> String): String{
    return func(msg)
}
fun evensFromList(list: List<Int>) = print(list.filter { it.toInt() % 2 != 0 })
