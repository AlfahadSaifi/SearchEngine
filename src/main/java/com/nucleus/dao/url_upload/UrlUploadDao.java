package com.nucleus.dao.url_upload;

import com.nucleus.dto.link.LinkDTO;
import com.nucleus.dto.file.PdfDTO;

public interface UrlUploadDao {
    boolean savaUrlData(LinkDTO linkDTO);
    boolean savaFileData(PdfDTO pdf);
    int getNextSequence();
}
