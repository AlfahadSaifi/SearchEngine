package com.nucleus.service.trie;


import com.nucleus.model.trie.FileTrie;
import com.nucleus.model.trie.Trie;
import com.nucleus.model.trie.UrlTrie;

public interface TrieService {
    Trie generateTrie();
    FileTrie generateFileTrie();
    UrlTrie generateUrlTrie();
}
