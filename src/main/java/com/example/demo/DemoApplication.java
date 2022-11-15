package com.example.demo;

import com.example.demo.entity.Characteristic;
import com.example.demo.entity.Subject;
import com.example.demo.repos.CharRepos;
import com.example.demo.repos.SubjectRepos;
import com.example.demo.service.MenuService;
import com.example.demo.service.ProcessAnaliz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private MenuService menuService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {

//		List<Characteristic> characteristics =
//				List.of(
//						Characteristic.builder().value("Крылья").build(),
//						Characteristic.builder().value("Лапки").build(),
//						Characteristic.builder().value("Хвост").build(),
//						Characteristic.builder().value("Зубы").build(),
//						Characteristic.builder().value("Шерсть").build(),
//						Characteristic.builder().value("Шасси").build(),
//						Characteristic.builder().value("Винт").build(),
//						Characteristic.builder().value("Нос").build(),
//						Characteristic.builder().value("Рот").build()
//				);
//
//		List<Subject> subjects = List.of(
//				Subject.builder().value("Птица").build(),
//				Subject.builder().value("Пёс").build(),
//				Subject.builder().value("Самолет").build()
//		);
        return (args) -> {
//
//			subjectRepos.saveAll(subjects);
//			charRepos.saveAll(characteristics);

//			process.process();
            Scanner scanner = new Scanner(System.in);
            int comm = -1;
            while (comm != 0) {
                System.out.println("--------------------------------\n" +
                        "1) Просмотреть все характеристики. \n" +
                        "2) Просмотреть все объекты.\n" +
                        "3) Создать характеристики.\n" +
                        "4) Создать объект \n" +
                        "5) Задать объектам характеристики \n" +
                        "6) Запустить систему \n" +
                        "--------------------------------" +
                        "Введите команду:");
                menuService.menu(comm = scanner.nextInt());

            }
        };
    }
}
