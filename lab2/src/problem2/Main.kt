package problem2

fun main(){
    fun String.monogram(): String {
        val names = this.split(" ")
        return names.map { it[0] }.joinToString (separator = "")
    }
    println("Fuzi Botond Erno".monogram())

    fun List<String>.joinedBy(separator:String) = this.joinToString(separator = separator)
    println((listOf<String>("apple", "pear", "melon").joinedBy("#")))

    fun List<String>.longestString() = this.sortedBy { it.length }[this.size-1]


    println((listOf<String>("apple", "pearssss", "melon").longestString()))


}