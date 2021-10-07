package objectives

interface IDictionary {
    companion object{
        val DICTIONARY_NAME="text.txt"
    }
    fun add(word:String):Boolean
    fun find(word:String):Boolean
    fun size():Int
}