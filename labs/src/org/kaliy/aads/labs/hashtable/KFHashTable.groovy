package org.kaliy.aads.labs.hashtable

import groovy.transform.ToString

class KFHashTable<K, V> implements Map<K, V> {

    private final static Integer INITIAL_CAPACITY = 2

    def private entries = new Entry<K, V>[INITIAL_CAPACITY]
    def private size = 0

    @Override
    int size() {
        size
    }

    @Override
    boolean isEmpty() {
        size > 0
    }

    @Override
    boolean containsKey(Object key) {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean containsValue(Object value) {
        throw new UnsupportedOperationException()
    }

    @Override
    V get(Object key) {
        def entry = entries[getIndexFor(key.hashCode())]
        if (entry && key == entry.key) {
            return entry.value
        } else { //TODO use iterators
            while (entry?.next) {
                entry = entry.next
                if (entry && key == entry.key) {
                    return entry.value
                }
            }
        }
        return null
    }

    @Override
    V put(K key, V value) {
        addEntry(key.hashCode(), key, value)
        value
    }

    @Override
    V remove(Object key) {
        throw new UnsupportedOperationException()
    }

    @Override
    void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException()
    }

    @Override
    void clear() {
        entries = new List<Entry<K, V>>[INITIAL_CAPACITY]
        size = 0
    }

    @Override
    Set<K> keySet() {
        throw new UnsupportedOperationException()
    }

    @Override
    Collection<V> values() {
        throw new UnsupportedOperationException()
    }

    @Override
    Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException()
    }

    @ToString
    class Entry<K, V> implements Iterable<Entry<K, V>> {
        K key
        V value
        Entry<K, V> next

        @Override
        Iterator<Entry<K, V>> iterator() {
            new Iterator<Entry<K, V>>() {
                @Override
                boolean hasNext() {
                    null == next
                }

                @Override
                Entry<K, V> next() {
                    next
                }

                @Override
                void remove() {
                    throw new UnsupportedOperationException()
                }
            }
        }
    }

    void addEntry(Integer hash, K key, V value) {
        if (size >= entries.size()) {
            resizeEntriesListTo(entries.size() * 2)
        }
        def index = getIndexFor(hash)
        def entry = new Entry<K, V>(key: key, value: value)
        if (null == entries[index]) {
            entries[index] = entry
        } else {
            def lastEntry = getLastEntryOfIndex(index, key)
            if (key == lastEntry.key) {
                lastEntry.value = value
                return
            } else {
                lastEntry.next = entry
            }
        }
        size++
    }

    private Entry<K, V> getLastEntryOfIndex(Integer index, K key) {
        def lastEntry = entries[index]
        while (null != lastEntry.next) {
            if (key == lastEntry.key) {
                return lastEntry
            }
            lastEntry = lastEntry.next
        }
        lastEntry
    }

    private Integer getIndexFor(Integer hash) {
        Math.abs(hash % entries.size())
    }

    def private resizeEntriesListTo(Integer newSize) {
        size = 0
        def oldEntries = entries.clone()
        entries = new Entry<K, V>[newSize]
        oldEntries.each { entry ->
            def currentEntry = entry
            if (currentEntry && !currentEntry.next) {
                put(currentEntry.key, currentEntry.value)
            } else {
                while (currentEntry) {
                    def temp = currentEntry.next
                    currentEntry.next = null
                    put(currentEntry.key, currentEntry.value)
                    currentEntry = temp
                }
            }
        }
    }

    String toString() {
        entries.toString()
    }
}
