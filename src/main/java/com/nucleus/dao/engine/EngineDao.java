package com.nucleus.dao.engine;

import com.nucleus.dto.file.PdfDTO;

import java.util.List;

public interface EngineDao {
    List<Object[]> retrieveSuggestions(String keyword);

    PdfDTO findByID(String id);
}
