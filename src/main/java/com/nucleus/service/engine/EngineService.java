package com.nucleus.service.engine;

import com.nucleus.dto.file.PdfDTO;
import com.nucleus.model.engine.Link;

import java.util.List;

public interface EngineService {
    List<Link> retrieveSuggestions(String keyword);
    List<Link> getSearchResponse(String[] searchKeywords);

    PdfDTO getPdfFile(String title);
}
