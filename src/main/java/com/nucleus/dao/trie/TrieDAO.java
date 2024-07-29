package com.nucleus.dao.trie;

import com.nucleus.model.engine.Link;
import com.nucleus.model.engine.Pdf;

import java.util.List;

public interface TrieDAO {
    List<Link> fetchUrlDataForTrieGeneration();
   List<String> fetchKeywordForTrieGeneration();
   List<Pdf> fetchFileDataForTrieGeneration();
   List<String> fetchKeywordFileForTrieGeneration();
}
