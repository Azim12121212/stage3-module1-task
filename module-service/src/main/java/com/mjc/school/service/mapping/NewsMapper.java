package com.mjc.school.service.mapping;

import com.mjc.school.service.dto.NewsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.mjc.school.repository.model.NewsModel;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO newsToNewsDTO(NewsModel newsModel);

    NewsModel newsDTOToNews(NewsDTO newsDTO);
}
