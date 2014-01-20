package org.kaliy.aads.labs.hashtable

class KFHashTableTest extends GroovyTestCase {

    KFHashTable hashTable

    @Override
    void setUp() {
        hashTable = new KFHashTable()
    }

    void testPuttingElementIncreasesMapSize() {
        hashTable.put "a", "a"
        hashTable.put "b", "a"

        assert 2 == hashTable.size()
    }

    void testPuttingElementWithSameKeyDoesntIncreaseSize() {
        hashTable.put "a", "a"
        hashTable.put "a", "b"

        assert 1 == hashTable.size()
    }

    void testPuttingElementWithSameKeyChangesValue() {
        hashTable.put "a", "a"
        hashTable.put "a", "b"

        assert "b" == hashTable.get("a")
    }

    void testGettingUnexistedElementReturnsNull() {
        hashTable.put "a", "a"
        hashTable.put "b", "b"
        hashTable.put "c", "c"

        assert null == hashTable.get("mansik")
    }

    void testAddingMoreThan8Elements() {
        hashTable.put "a", "a"
        hashTable.put "b", "b"
        hashTable.put "c", "c"
        hashTable.put "1", "d"
        hashTable.put "2", "e"
        hashTable.put "3", "f"
        hashTable.put "4", "g"
        hashTable.put "5", "h"
        hashTable.put "6", "i"

        assert 9 == hashTable.size()
        assert "a" == hashTable.get("a")
        assert "b" == hashTable.get("b")
        assert "c" == hashTable.get("c")
        assert "d" == hashTable.get("1")
        assert "e" == hashTable.get("2")
        assert "f" == hashTable.get("3")
        assert "g" == hashTable.get("4")
        assert "h" == hashTable.get("5")
        assert "i" == hashTable.get("6")
    }

}
