package objectives

import java.io.File
import java.io.InputStream

object HashSetDictionary:IDictionary {
    val words = HashSet<String>()

    init {
        val inputStream: InputStream = File(IDictionary.DICTIONARY_NAME).inputStream()

        inputStream.bufferedReader().forEachLine { words.add(it) }
    }
    override fun add(word: String): Boolean {
        return words.add(word)

    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}