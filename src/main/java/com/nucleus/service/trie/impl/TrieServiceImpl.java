package com.nucleus.service.trie.impl;

import com.nucleus.dao.trie.TrieDAO;
import com.nucleus.model.engine.Link;
import com.nucleus.model.engine.Pdf;
import com.nucleus.model.trie.FileTrie;
import com.nucleus.model.trie.Trie;
import com.nucleus.model.trie.UrlTrie;
import com.nucleus.service.trie.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrieServiceImpl implements TrieService {

    @Autowired
    TrieDAO trieDAO;

    @Override
    public Trie generateTrie() {
        Trie trie=new Trie();
        List<String> searchList=trieDAO.fetchKeywordForTrieGeneration();
        List<String> searchFileList=trieDAO.fetchKeywordFileForTrieGeneration();

        for (String st:searchList){
             trie.insert(st);
        }
        for (String st:searchFileList){
            trie.insert(st);
        }

        return trie;
    }

    @Override
    public FileTrie generateFileTrie() {
        List<Pdf> pdfList = trieDAO.fetchFileDataForTrieGeneration();

        FileTrie fileTrie=new FileTrie();

        for (Pdf st:pdfList){
            fileTrie.insert(st);
        }

        return fileTrie;
    }

    @Override
    public UrlTrie generateUrlTrie() {
        List<Link> links = trieDAO.fetchUrlDataForTrieGeneration();

        UrlTrie urlTrie=new UrlTrie();

        for (Link link : links){
            urlTrie.insert(link);
        }

        return urlTrie;
    }

}
