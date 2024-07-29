package com.nucleus.service.engine.impl;

import com.nucleus.dao.engine.EngineDao;
import com.nucleus.dto.file.PdfDTO;
import com.nucleus.model.engine.Link;
import com.nucleus.service.engine.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EngineServiceImpl implements EngineService {
    @Autowired
    private EngineDao engineDao;

    @Override
    public List<Link> retrieveSuggestions(String keyword) {
        if (keyword != null){
            List<Link> finalList= new ArrayList<>();
            List<Object[]> result=engineDao.retrieveSuggestions(keyword);
            for(Object[] obj:result){
                Link l= new Link();
                l.setId((String) obj[0]);
                l.setUrlTitle((String) obj[1]);
                l.setUrl((String) obj[2]);
                l.setUrlDescription((String) obj[3]);
                finalList.add(l);
            }
            return finalList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Link> getSearchResponse(String[] searchKeywords) {
        if(searchKeywords==null || searchKeywords.length==0){
            return new ArrayList<>();
        }
        List<Link> fetchedResult = new ArrayList<>();

        for(String keyword: searchKeywords){
            List<Link> links = retrieveSuggestions(keyword);
            fetchedResult.addAll(links);
        }

        return fetchedResult;
    }

    @Override
    public PdfDTO getPdfFile(String title) {
        return engineDao.findByID(title);
    }
}
