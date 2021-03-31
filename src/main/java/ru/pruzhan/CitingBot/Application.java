package ru.pruzhan.CitingBot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс, содержащий в себе точку входа в приложение
 * @author Dmitriy Fedrushkov
 */

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
