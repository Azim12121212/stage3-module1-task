package com.mjc.school.service.mapping;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-15T23:44:05+0600",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsToNewsDTO(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( news.getId() );
        newsDTO.setTitle( news.getTitle() );
        newsDTO.setContent( news.getContent() );
        newsDTO.setCreateDate( news.getCreateDate() );
        newsDTO.setLastUpdatedDate( news.getLastUpdatedDate() );
        newsDTO.setAuthorId( news.getAuthorId() );

        return newsDTO;
    }

    @Override
    public News newsDTOToNews(NewsDTO newsDTO) {
        if ( newsDTO == null ) {
            return null;
        }

        News news = new News();

        news.setId( newsDTO.getId() );
        news.setTitle( newsDTO.getTitle() );
        news.setContent( newsDTO.getContent() );
        news.setCreateDate( newsDTO.getCreateDate() );
        news.setLastUpdatedDate( newsDTO.getLastUpdatedDate() );
        news.setAuthorId( newsDTO.getAuthorId() );

        return news;
    }
}
