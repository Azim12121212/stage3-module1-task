package com.mjc.school.repository.impl;

import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.model.*;
import com.mjc.school.repository.datasource.DataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class NewsRepository implements GeneralRepository<News> {
    private final DataSource dataSource;

    private final DateTimeFormatter MY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public NewsRepository() {
        this.dataSource = DataSource.getInstance();
    }

    @Override
    public News create(News news) {
        Long id = (long) (dataSource.getNewsList().size()+1);
        news.setId(id);
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        news.setCreateDate(time);
        news.setLastUpdatedDate(time);
        dataSource.getNewsList().add(news);
        return news;
    }

    @Override
    public List<News> getAll() {
        return dataSource.getNewsList();
    }

    @Override
    public News getById(Long id) {
        for (News news: dataSource.getNewsList()) {
            if (Objects.equals(id, news.getId())) {
                return news;
            }
        }
        return null;
    }

    @Override
    public News update(News news) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(MY_FORMAT));
        News newsInList = getById(news.getId());
        if (newsInList!=null) {
            int index = dataSource.getNewsList().indexOf(newsInList);
            dataSource.getNewsList().get(index).setTitle(news.getTitle());
            dataSource.getNewsList().get(index).setContent(news.getContent());
            dataSource.getNewsList().get(index).setLastUpdatedDate(time);
            dataSource.getNewsList().get(index).setAuthorId(news.getAuthorId());
            return dataSource.getNewsList().get(index);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        News news = getById(id);
        if (news!=null) {
            dataSource.getNewsList().remove(news);
            return true;
        }
        return false;
    }

    public List<Author> getAuthorList() {
        return dataSource.getAuthorList();
    }
}
