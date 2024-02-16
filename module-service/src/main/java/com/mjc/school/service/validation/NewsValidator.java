package com.mjc.school.service.validation;

import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NewsException;

public class NewsValidator {
    private static NewsValidator instance;
    private static final int NEWS_TITLE_MIN = 5;
    private static final int NEWS_TITLE_MAX = 30;
    private static final int NEWS_CONTENT_MIN = 5;
    private static final int NEWS_CONTENT_MAX = 255;

    private NewsValidator() {
    }

    public static NewsValidator getInstance() {
        if (instance==null) {
            synchronized (NewsValidator.class) {
                if (instance==null) {
                    instance = new NewsValidator();
                }
            }
        }
        return instance;
    }

    public void checkNewsId(String id) throws NewsException {
        // first check news id format
        if (!isNumeric(id)) {
            throw new NewsException(Errors.ERROR_NEWS_ID_FORMAT.getErrorData());
        }
        // then check news id value
        if (id==null || Long.parseLong(id)<1) {
            throw new NewsException(Errors.ERROR_NEWS_ID_VALUE.getErrorData(id));
        }
    }

    public void checkNewsTitle(String title) throws NewsException {
        if (title.length()<NEWS_TITLE_MIN || title.length()>NEWS_TITLE_MAX) {
            throw new NewsException(Errors.ERROR_NEWS_TITLE_LENGTH.getErrorData(title));
        }
    }

    public void checkNewsContent(String content) throws NewsException {
        if (content.length()<NEWS_CONTENT_MIN || content.length()>NEWS_CONTENT_MAX) {
            throw new NewsException(Errors.ERROR_NEWS_CONTENT_LENGTH.getErrorData(content));
        }
    }

    public void checkNewsAuthorId(String authorId) throws NewsException {
        // first check news authorId format
        if (!isNumeric(authorId)) {
            throw new NewsException(Errors.ERROR_NEWS_AUTHOR_ID_FORMAT.getErrorData());
        }
        // then check news authorId value
        if (authorId==null || Long.parseLong(authorId)<1) {
            throw new NewsException(Errors.ERROR_NEWS_AUTHOR_ID_VALUE.getErrorData(authorId));
        }
    }

    private boolean isNumeric(String str) {
        char[] chars = str.toCharArray();
        int counter=0;
        if (chars[0]=='-') {
            counter++;
        }
        for (int i=0; i<chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                counter++;
            }
        }
        return counter==chars.length;
    }
}
