package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.GeneralService;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NewsException;
import com.mjc.school.service.mapping.NewsMapper;
import com.mjc.school.service.validation.NewsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsService implements GeneralService<NewsDTO> {
    private static NewsService instance;
    private final NewsRepository newsRepository;
    private final NewsValidator newsValidator;
    private final List<NewsDTO> newsDTOList;

    private NewsService() {
        newsRepository = new NewsRepository();
        newsValidator = NewsValidator.getInstance();
        newsDTOList = new ArrayList<>();
    }

    public static NewsService getInstance() {
        if (instance==null) {
            synchronized (NewsService.class) {
                if (instance==null) {
                    instance = new NewsService();
                }
            }
        }
        return instance;
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) throws NewsException {
        newsValidator.checkNewsTitle(newsDTO.getTitle());
        newsValidator.checkNewsContent(newsDTO.getContent());
        newsValidator.checkNewsAuthorId(String.valueOf(newsDTO.getAuthorId()));
        if (!doesAuthorIdExist(newsDTO.getAuthorId())) {
            throw new NewsException(Errors.ERROR_NEWS_AUTHOR_ID_NOT_EXIST.getErrorData(newsDTO.getAuthorId(),false));
        }
        NewsModel newsModel = newsRepository.create(NewsMapper.INSTANCE.newsDTOToNews(newsDTO));
        return NewsMapper.INSTANCE.newsToNewsDTO(newsModel);
    }

    @Override
    public List<NewsDTO> readAll() {
        newsDTOList.clear();
        for (NewsModel newsModel : newsRepository.readAll()) {
            newsDTOList.add(NewsMapper.INSTANCE.newsToNewsDTO(newsModel));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTO readById(Long id) throws NewsException {
        NewsDTO newsDTO = NewsMapper.INSTANCE.newsToNewsDTO(newsRepository.readById(id));
        if (newsDTO!=null) {
            return newsDTO;
        } else {
            throw new NewsException(Errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(id,true));
        }
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) throws NewsException {
        if (readById(newsDTO.getId())==null) {
            throw new NewsException(Errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(newsDTO.getId(),true));
        }
        newsValidator.checkNewsTitle(newsDTO.getTitle());
        newsValidator.checkNewsContent(newsDTO.getContent());
        newsValidator.checkNewsAuthorId(String.valueOf(newsDTO.getAuthorId()));
        if (!doesAuthorIdExist(newsDTO.getAuthorId())) {
            throw new NewsException(Errors.ERROR_NEWS_AUTHOR_ID_NOT_EXIST.getErrorData(newsDTO.getAuthorId(),false));
        }
        NewsModel newsModel = newsRepository.update(NewsMapper.INSTANCE.newsDTOToNews(newsDTO));
        return NewsMapper.INSTANCE.newsToNewsDTO(newsModel);
    }

    @Override
    public Boolean delete(Long id) throws NewsException {
        if (readById(id)==null) {
            throw new NewsException(Errors.ERROR_NEWS_ID_NOT_EXIST.getErrorData(id,true));
        }
        return newsRepository.delete(id);
    }

    private boolean doesAuthorIdExist(Long authorId) {
        for (AuthorModel authorModel : newsRepository.getAuthorList()) {
            if (Objects.equals(authorId, authorModel.getId())) {
                return true;
            }
        }
        return false;
    }
}
