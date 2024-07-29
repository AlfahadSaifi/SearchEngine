package com.nucleus.controller.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleus.dto.file.PdfDTO;
import com.nucleus.model.engine.Link;
import com.nucleus.model.engine.Pdf;
import com.nucleus.model.trie.FileTrie;
import com.nucleus.model.trie.Trie;
import com.nucleus.model.trie.UrlTrie;
import com.nucleus.service.engine.EngineService;
import com.nucleus.service.trie.TrieService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
@RequestMapping("/search")
public class SearchEngineController {
    @Autowired
    private Logger logger;
    @Autowired
    private EngineService engineService;
    @Autowired
    TrieService trieService;
    private Trie trie;
    private FileTrie fileTrie;
    private UrlTrie urlTrie;

    @GetMapping
    public String getSearchPage(Model model){
        trie = trieService.generateTrie();
        urlTrie = trieService.generateUrlTrie();
        fileTrie = trieService.generateFileTrie();
        return "engine/searchPage";
    }

    @GetMapping(value="/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchSuggestions(@RequestParam(required = false , name = "keyword") String keyword) {

//        using trie search
        List<String> links = trie.prefixSearch(keyword.toLowerCase());
        Set<String> keywords = new HashSet<>(links);

        try {
            ObjectMapper objectMapper= new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(new ArrayList<>(keywords));
            return ResponseEntity.ok(jsonData);
        } catch (JsonProcessingException e) {
            logger.info(e);
            return ResponseEntity.ok("No Keyword Found");
        }
    }

    @GetMapping("/response/{keyword}")
    public String fetchResponse(@PathVariable("keyword") String searchQuery,
                                Model model){

        String[] searchKeywords = searchQuery.toLowerCase().split(" ");

//        fetching the url results
        List<Link> fetchedUrlResult = new ArrayList<>();
        for (String keyword : searchKeywords){
            fetchedUrlResult.addAll(urlTrie.search(keyword));
        }

//        fetching the files result
        List<Pdf> fetchedFileResult = new ArrayList<>();
        for (String keyword : searchKeywords){
            fetchedFileResult.addAll(fileTrie.search(keyword));
        }

        model.addAttribute("fetchedResults",fetchedUrlResult);
        model.addAttribute("fetchedFileResults",fetchedFileResult);
        model.addAttribute("searchQuery",searchQuery);

        return "engine/searchResponse";
    }

    // this will preview the pdf in next tab
    @GetMapping("/response/getPdf")
    public String previewFile(@RequestParam("id") String id, Model model) {

        // Fetch the PDF File
        PdfDTO pdfFile = engineService.getPdfFile(id);
        byte[] pdfContent = pdfFile.getFileData();

        // Add content of PDF file in model
        model.addAttribute("pdfBytes", pdfContent);
        model.addAttribute("pdfTitle", pdfFile.getFileTitle());

        return "engine/viewPdf";
    }

}
