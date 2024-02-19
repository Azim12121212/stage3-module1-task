package com.mjc.school.controller.menu;

import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.errorsexceptions.Errors;
import com.mjc.school.service.errorsexceptions.NewsException;
import com.mjc.school.service.validation.NewsValidator;

import java.util.Scanner;

public class AppMenuController {
    private final String ENTER_NUMBER_OF_OPERATION = "Enter the number of operation:";
    private final String ENTER_NEWS_ID = "Enter news id:";
    private final String ENTER_NEWS_TITLE = "Enter news title:";
    private final String ENTER_NEWS_CONTENT = "Enter news content:";
    private final String ENTER_NEWS_AUTHOR_ID = "Enter author id:";
    private final String OPERATION = "Operation: ";
    private final Scanner scanner;
    private final NewsController newsController;
    private final NewsValidator newsValidator;

    public AppMenuController() {
        scanner = new Scanner(System.in);
        newsController = new NewsController();
        newsValidator = NewsValidator.getInstance();
    }

    public void runApp() {
        while (true) {
            // program prints out app menu
            System.out.println(ENTER_NUMBER_OF_OPERATION);
            for (MenuOptions options: MenuOptions.values()) {
                System.out.println(options.getOptionCode() + " - " + options.getOptionName());
            }
            // client chooses menu option
            switch (scanner.nextLine()) {
                case "1":
                    getAllNewsOption();
                    break;
                case "2":
                    getNewsByIdOption(scanner);
                    break;
                case "3":
                    createNewsOption(scanner);
                    break;
                case "4":
                    updateNewsOption(scanner);
                    break;
                case "5":
                    removeNewsByIdOption(scanner);
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println(Errors.ERROR_COMMAND_NOT_FOUND.getErrorMessage());
            }
        }
    }

    private void getAllNewsOption() {
        System.out.println(OPERATION + MenuOptions.GET_ALL_NEWS.getOptionName());
        for (NewsDTO dto: newsController.readAll()) {
            System.out.println(dto);
        }
    }

    private void getNewsByIdOption(Scanner scanner) {
        System.out.println(OPERATION + MenuOptions.GET_NEWS_BY_ID.getOptionName());
        try {
            System.out.println(ENTER_NEWS_ID);
            String id = scanner.nextLine();
            newsValidator.checkNewsId(id);
            System.out.println(newsController.readById(Long.parseLong(id)));
        } catch (NewsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createNewsOption(Scanner scanner) {
        System.out.println(OPERATION + MenuOptions.CREATE_NEWS.getOptionName());
        try {
            System.out.println(ENTER_NEWS_TITLE);
            String title = scanner.nextLine();
            System.out.println(ENTER_NEWS_CONTENT);
            String content = scanner.nextLine();
            System.out.println(ENTER_NEWS_AUTHOR_ID);
            String authorId = scanner.nextLine();
            newsValidator.checkNewsAuthorId(authorId);
            NewsDTO newsDTO = new NewsDTO(title, content, Long.parseLong(authorId));
            System.out.println(newsController.create(newsDTO));
        } catch (NewsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateNewsOption(Scanner scanner) {
        System.out.println(OPERATION + MenuOptions.UPDATE_NEWS.getOptionName());
        try {
            System.out.println(ENTER_NEWS_ID);
            String id = scanner.nextLine();
            newsValidator.checkNewsId(id);

            System.out.println(ENTER_NEWS_TITLE);
            String title = scanner.nextLine();
            System.out.println(ENTER_NEWS_CONTENT);
            String content = scanner.nextLine();

            System.out.println(ENTER_NEWS_AUTHOR_ID);
            String authorId = scanner.nextLine();
            newsValidator.checkNewsAuthorId(authorId);

            NewsDTO newsDTO = new NewsDTO(title, content, Long.parseLong(authorId));
            newsDTO.setId(Long.parseLong(id));
            System.out.println(newsController.update(newsDTO));
        } catch (NewsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeNewsByIdOption(Scanner scanner) {
        System.out.println(OPERATION + MenuOptions.REMOVE_NEWS_BY_ID.getOptionName());
        try {
            System.out.println(ENTER_NEWS_ID);
            String id = scanner.nextLine();
            newsValidator.checkNewsId(id);
            System.out.println(newsController.delete(Long.parseLong(id)));
        } catch (NewsException e) {
            System.out.println(e.getMessage());
        }
    }
}
