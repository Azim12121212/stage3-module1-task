package com.mjc.school.repository.impl;

import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.model.*;
import com.mjc.school.repository.datasource.DataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class NewsRepository implements GeneralRepository<NewsModel> {
    private final DataSource dataSource;

    private final DateTimeFormatter MY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public NewsRepository() {
        this.dataSource = DataSource.getInstance();
    }

    @Override
    public NewsModel create(NewsModel newsModel) {
        Long id = (long) (dataSource.getNewsList().size()+1);
        newsModel.setId(id);
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        newsModel.setCreateDate(time);
        newsModel.setLastUpdatedDate(time);
        dataSource.getNewsList().add(newsModel);
        return newsModel;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsList();
    }

    @Override
    public NewsModel readById(Long id) {
        for (NewsModel newsModel : dataSource.getNewsList()) {
            if (Objects.equals(id, newsModel.getId())) {
                return newsModel;
            }
        }
        return null;
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        NewsModel newsModelInList = readById(newsModel.getId());
        if (newsModelInList !=null) {
            int index = dataSource.getNewsList().indexOf(newsModelInList);
            dataSource.getNewsList().get(index).setTitle(newsModel.getTitle());
            dataSource.getNewsList().get(index).setContent(newsModel.getContent());
            dataSource.getNewsList().get(index).setLastUpdatedDate(time);
            dataSource.getNewsList().get(index).setAuthorId(newsModel.getAuthorId());
            return dataSource.getNewsList().get(index);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        NewsModel newsModel = readById(id);
        if (newsModel !=null) {
            dataSource.getNewsList().remove(newsModel);
            return true;
        }
        return false;
    }

    public List<AuthorModel> getAuthorList() {
        return dataSource.getAuthorList();
    }
}
