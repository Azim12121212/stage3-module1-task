package com.mjc.school.service.mapping;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T07:51:36+0600",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsToNewsDTO(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( newsModel.getId() );
        newsDTO.setTitle( newsModel.getTitle() );
        newsDTO.setContent( newsModel.getContent() );
        newsDTO.setCreateDate( newsModel.getCreateDate() );
        newsDTO.setLastUpdatedDate( newsModel.getLastUpdatedDate() );
        newsDTO.setAuthorId( newsModel.getAuthorId() );

        return newsDTO;
    }

    @Override
    public NewsModel newsDTOToNews(NewsDTO newsDTO) {
        if ( newsDTO == null ) {
            return null;
        }

        NewsModel newsModel = new NewsModel();

        newsModel.setId( newsDTO.getId() );
        newsModel.setTitle( newsDTO.getTitle() );
        newsModel.setContent( newsDTO.getContent() );
        newsModel.setCreateDate( newsDTO.getCreateDate() );
        newsModel.setLastUpdatedDate( newsDTO.getLastUpdatedDate() );
        newsModel.setAuthorId( newsDTO.getAuthorId() );

        return newsModel;
    }
}
