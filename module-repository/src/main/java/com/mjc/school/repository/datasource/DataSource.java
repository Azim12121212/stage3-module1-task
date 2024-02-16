package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSource {
    private static DataSource instance;
    private List<Author> authorList;
    private List<News> newsList;
    private Long authorId;
    private Long newsId;

    private final String AUTHOR_PATH = "module-repository/src/main/resources/author.txt";
    private final String CONTENT_PATH = "module-repository/src/main/resources/content.txt";
    private final String NEWS_PATH = "module-repository/src/main/resources/news.txt";
    private static final int MAX_AMOUNT_OF_NEWS = 20;

    private DataSource() {
        authorId = 1L;
        newsId = 1L;
        readAuthorListFromDataSource();
        readNewsListFromDataSource();
    }

    public static DataSource getInstance() {
        if (instance==null) {
            synchronized (DataSource.class) {
                if (instance==null) {
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    private void readAuthorListFromDataSource() {
        authorList = new ArrayList<>();
        List<String> authorNames = readFromDataSource(AUTHOR_PATH);
        for (String s: authorNames) {
            Author author = new Author(authorId, s);
            authorList.add(author);
            authorId++;
        }
    }

    private void readNewsListFromDataSource() {
        newsList = new ArrayList<>();
        List<String> contentString = readFromDataSource(CONTENT_PATH);
        List<String> newsString = readFromDataSource(NEWS_PATH);

        // Split our newsString by titles, createDates, lastUpdatedDates, authorIds
        List<String> newsTitles = new ArrayList<>();
        List<String> newsCreateDates = new ArrayList<>();
        List<String> newsLastUpdatedDates = new ArrayList<>();
        List<String> newsAuthorIds = new ArrayList<>();

        int titlesCounter=0;
        int createDatesCounter=1;
        int lastUpdatedDatesCounter=2;
        int authorIdsCounter=3;
        for (int i=0; i<newsString.size(); i++) {
            if (i==titlesCounter) {
                newsTitles.add(newsString.get(i));
                titlesCounter+=4;
            } else if (i==createDatesCounter) {
                newsCreateDates.add(newsString.get(i));
                createDatesCounter+=4;
            } else if (i==lastUpdatedDatesCounter) {
                newsLastUpdatedDates.add(newsString.get(i));
                lastUpdatedDatesCounter+=4;
            } else if (i==authorIdsCounter) {
                newsAuthorIds.add(newsString.get(i));
                authorIdsCounter+=4;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        for (int i=0; i<MAX_AMOUNT_OF_NEWS; i++) {
            News news = new News(newsId, newsTitles.get(i), contentString.get(i),
                    LocalDateTime.parse(newsCreateDates.get(i), formatter),
                    LocalDateTime.parse(newsLastUpdatedDates.get(i), formatter),
                    Long.parseLong(newsAuthorIds.get(i)));
            newsList.add(news);
            newsId++;
        }
    }

    /**
     * The method takes file path as a parameter,
     * reads through the data source, writes all the data from
     * data source into the list of Strings and finally returns this list
     *
     * @param filepath
     * @return List
     */
    private List<String> readFromDataSource(String filepath) {
        List<String> lines = new ArrayList<>();
        try {
            File f = new File(filepath);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: readFromDataSource");
        }
        return lines;
    }
}
