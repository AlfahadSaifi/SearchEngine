package com.nucleus.model.trie;


import java.util.HashMap;

import java.util.Map;

class TrieNode {
    //    Map<Character, TrieNode> children;
//    List<Link> searchTiles;
//
//    public TrieNode() {
//        children = new HashMap<>();
//        searchTiles = new ArrayList<>();
//    }
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;


}

