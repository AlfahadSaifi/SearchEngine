package com.nucleus.mapper.link;

import com.nucleus.dto.link.LinkDTO;
import com.nucleus.model.engine.Link;

public class LinkMapper {
    private LinkMapper(){}

    public static LinkDTO toDTO(Link link) {
        if (link == null) {
            return null;
        }

        return new LinkDTO(
                link.getId(),
                link.getUrlTitle(),
                link.getUrl(),
                link.getUrlKeywords(),
                link.getUrlDescription()
        );
    }

    public static Link toEntity(LinkDTO linkDTO) {
        if (linkDTO == null) {
            return null;
        }
        Link link = new Link();
        link.setId(linkDTO.getId());
        link.setUrlTitle(linkDTO.getUrlTitle());
        link.setUrl(linkDTO.getUrl());
        link.setUrlKeywords(linkDTO.getUrlKeywords());
        link.setUrlDescription(linkDTO.getUrlDescription());
        return link;
    }
}
