package com.nucleus.model.trie;

import com.nucleus.model.engine.Link;

import java.util.*;

public class UrlTrie {
    private final UrlTrieNode root;

    public UrlTrie() {
        root = new UrlTrieNode();
    }

    public void insert(Link link) {
        for (String keyword : link.getUrlKeywords()) {
            UrlTrieNode currentNode = root;
            for (char c : keyword.toCharArray()) {
                currentNode = currentNode.children.computeIfAbsent(c, k -> new UrlTrieNode());
            }

            boolean check=false;
            for(Link s: currentNode.searchTiles){
                if(s.getUrlTitle().equalsIgnoreCase(link.getUrlTitle())){
                    check=true;
                }
            }
            if (!check)
                currentNode.searchTiles.add(link);
        }
    }

    public List<Link> search(String keywordPrefix) {
        UrlTrieNode currentNode = root;
        for (char c : keywordPrefix.toCharArray()) {
            currentNode = currentNode.children.get(c);
            if (currentNode == null) {
                return new ArrayList<>();
            }
        }
        return collectAllSearchTiles(currentNode);
    }

    private List<Link> collectAllSearchTiles(UrlTrieNode node) {
        Set<Link> result = new HashSet<>(node.searchTiles);
        for (Map.Entry<Character, UrlTrieNode> entry : node.children.entrySet()) {
            result.addAll(collectAllSearchTiles(entry.getValue()));
        }
        return new ArrayList<>(result);
    }
}
