package objectives

class DictionaryProvider {
    companion object{
        fun createDictionary(dictionaryType:DictionaryType):IDictionary{
            return when(dictionaryType){
                DictionaryType.ARRAY_LIST -> ListDictionary
                DictionaryType.TREE_SET -> TreeSetDictionary
                DictionaryType.HASH_SET ->  HashSetDictionary
            }
        }
    }

}