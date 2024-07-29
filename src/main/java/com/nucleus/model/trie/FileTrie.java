package com.nucleus.model.trie;

import com.nucleus.model.engine.Pdf;

import java.util.*;

public class FileTrie {
    private final FileTrieNode root;

    public FileTrie() {
        root = new FileTrieNode();
    }

    public void insert(Pdf pdf) {
        for (String keyword : pdf.getFileKeywords()) {
            FileTrieNode currentNode = root;
            for (char c : keyword.toCharArray()) {
                currentNode = currentNode.children.computeIfAbsent(c, k -> new FileTrieNode());
            }

            boolean check=false;
            for(Pdf s: currentNode.searchTiles){
                if(s.getFileTitle().equalsIgnoreCase(pdf.getFileTitle())){
                    check=true;
                }
            }
            if (!check)
                currentNode.searchTiles.add(pdf);
        }
    }

    public List<Pdf> search(String keywordPrefix) {
        FileTrieNode currentNode = root;
        for (char c : keywordPrefix.toCharArray()) {
            currentNode = currentNode.children.get(c);
            if (currentNode == null) {
                return new ArrayList<>();
            }
        }
        return collectAllSearchTiles(currentNode);
    }

    private List<Pdf> collectAllSearchTiles(FileTrieNode node) {
        Set<Pdf> result = new HashSet<>(node.searchTiles);
        for (Map.Entry<Character, FileTrieNode> entry : node.children.entrySet()) {
            result.addAll(collectAllSearchTiles(entry.getValue()));
        }
        return new ArrayList<>(result);
    }
}
