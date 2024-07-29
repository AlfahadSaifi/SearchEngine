package com.nucleus.service.url_upload;

import com.nucleus.dto.link.LinkDTO;
import com.nucleus.dto.file.PdfDTO;

public interface UrlUploadService {
    boolean savaUrlData(LinkDTO linkDTO , String keyword);
    boolean savaFileData(PdfDTO pdf , String keyword);
    int getNextSequence();
}
