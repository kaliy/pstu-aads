package org.kaliy.aads.labs.huffman

class HuffmanCoder {
    static class HuffmanTree implements Comparable<HuffmanTree> {
        Integer frequency

        HuffmanTree(freq) {
            frequency = freq
        }

        @Override
        int compareTo(HuffmanTree tree) {
            frequency - tree.frequency
        }
    }

    static class HuffmanLeaf extends HuffmanTree {
        Character value

        HuffmanLeaf(int freq, char val) {
            super(freq)
            value = val
        }
    }

    static class HuffmanNode extends HuffmanTree {
        HuffmanTree left, right

        HuffmanNode(HuffmanTree l, HuffmanTree r) {
            super(l.frequency + r.frequency)
            left = l
            right = r
        }
    }

    def charMap = [:]

    public HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = [] as PriorityQueue<HuffmanTree>
        for (Integer i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                trees.offer(new HuffmanLeaf(charFreqs[i], i as Character))
            }
        }
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree a = trees.poll()
            HuffmanTree b = trees.poll()
            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b))
        }
        trees.poll()
    }

    def printCodes(HuffmanTree tree, StringBuilder prefix) {
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf) tree
            charMap[prefix as String] = leaf.value
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode) tree
            // traverse left
            prefix.append('0') // TODO: bitwise operations, not strings
            printCodes(node.left, prefix)
            prefix.deleteCharAt(prefix.length() - 1)
            // traverse right
            prefix.append('1')
            printCodes(node.right, prefix)
            prefix.deleteCharAt(prefix.length() - 1)
        }
        charMap
    }

    public static void main(String[] args) {
        String test = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
        // TODO: handle multibytes encoding
        int[] charFreqs = new int[256]
        for (Character c : test.toCharArray()) {
            charFreqs[c as Integer]++
        }

        def coder = new HuffmanCoder()
        HuffmanTree tree = coder.buildTree(charFreqs)
        println coder.printCodes(tree, new StringBuilder())

    }

}
