package com.nucleus.model.trie;

import com.nucleus.model.engine.Pdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class FileTrieNode {
    Map<Character, FileTrieNode> children;
    List<Pdf> searchTiles;

    public FileTrieNode() {
        children = new HashMap<>();
        searchTiles = new ArrayList<>();
    }
}