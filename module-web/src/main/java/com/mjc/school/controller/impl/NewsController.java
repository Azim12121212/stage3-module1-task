package com.mjc.school.controller.impl;

import com.mjc.school.controller.GeneralController;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.errorsexceptions.NewsException;
import com.mjc.school.service.impl.NewsService;

import java.util.List;

public class NewsController implements GeneralController<NewsDTO> {
    private final NewsService newsService;

    public NewsController() {
        newsService = NewsService.getInstance();
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) throws NewsException {
        return newsService.create(newsDTO);
    }

    @Override
    public List<NewsDTO> getAll() {
        return newsService.getAll();
    }

    @Override
    public NewsDTO getById(Long id) throws NewsException {
        return newsService.getById(id);
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) throws NewsException {
        return newsService.update(newsDTO);
    }

    @Override
    public boolean delete(Long id) throws NewsException {
        return newsService.delete(id);
    }
}
