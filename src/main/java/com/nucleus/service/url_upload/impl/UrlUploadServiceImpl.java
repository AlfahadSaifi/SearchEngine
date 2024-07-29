package com.nucleus.service.url_upload.impl;

import com.nucleus.dao.url_upload.UrlUploadDao;
import com.nucleus.dto.link.LinkDTO;
import com.nucleus.dto.file.PdfDTO;
import com.nucleus.service.url_upload.UrlUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlUploadServiceImpl implements UrlUploadService {
    @Autowired
    private UrlUploadDao urlUploadDao;

    @Autowired
    private Logger logger;

    @Override
    public boolean savaUrlData(LinkDTO linkDTO , String keyword) {
        logger.info("(Service) Url Data : " + linkDTO);

        if(linkDTO==null){
            return false;
        }

        //retrieving the keyword list
        List<String> keywords = getKeywordsList(keyword);
        //setting the keyword list
        linkDTO.setUrlKeywords(keywords);

        return urlUploadDao.savaUrlData(linkDTO);
    }

    @Override
    public boolean savaFileData(PdfDTO pdf , String keyword) {
        logger.info("(Service) File Data : " + pdf);

        if(pdf==null){
            return false;
        }

        //retrieving the keyword list
        List<String> keywords = getKeywordsList(keyword);
        //setting the keyword list
        pdf.setFileKeywords(keywords);

        return urlUploadDao.savaFileData(pdf);
    }

    private static List<String> getKeywordsList(String keyword) {
        List<String> keywords = new ArrayList<>();
        String [] strings= keyword.split(",");
        for(String s :  strings){
            keywords.add(s.toLowerCase().trim());
        }
        return keywords;
    }

    @Override
    public int getNextSequence() {
        return urlUploadDao.getNextSequence();
    }
}