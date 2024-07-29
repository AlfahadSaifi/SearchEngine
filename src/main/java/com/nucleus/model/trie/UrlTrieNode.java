package com.nucleus.model.trie;

import com.nucleus.model.engine.Link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UrlTrieNode {
    Map<Character, UrlTrieNode> children;
    List<Link> searchTiles;

    public UrlTrieNode() {
        children = new HashMap<>();
        searchTiles = new ArrayList<>();
    }
}
