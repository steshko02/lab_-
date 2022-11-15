package com.example.demo.service;

import com.example.demo.entity.Characteristic;
import com.example.demo.entity.Subject;
import com.example.demo.repos.CharRepos;
import com.example.demo.repos.SubjectRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private CharRepos charRepos;

    @Autowired
    private SubjectRepos subjectRepos;

    @Transactional
    public void menu(int command) {

        Scanner scanner = new Scanner(System.in);

        switch (command) {
            case 1: {
                System.out.println(" Просмотреть все характеристики.");
                charRepos.findAll().forEach(e -> System.out.println(e.getValue()));
                break;
            }
            case 2:
                System.out.println(" Просмотреть все объекты.");
                subjectRepos.findAll().forEach(e -> System.out.println(e.getValue() + " \n" + e.getCharacteristics()));
                break;
            case 3:
                System.out.println("Создать характеристики.");
                String newChar = scanner.nextLine();
                charRepos.save(Characteristic.builder().value(newChar).build());
                break;
            case 4:
                System.out.println("Создать объекты.");
                String newSubject = scanner.nextLine();
                subjectRepos.save(Subject.builder().value(newSubject).build());
                break;

            case 5:
                System.out.println("Задать объектам характеристики.");
                System.out.println("Введите название объекта:");
                String subject = scanner.nextLine();
                int comm = 1;

                Subject subject1 = subjectRepos.findByValue(subject).orElse(null);

                while (comm != 0 && subject1 != null) {
                    Scanner scannerNew = new Scanner(System.in);
                    System.out.println("Хотите добавить свойство к " + subject + " (0 - нет/1-да)");
                    comm = scanner.nextInt();
                    if(comm == 0){
                        break;
                    }
                        System.out.println("Введите название характеристики, которую хотите добавить: ");
                        String val = scannerNew.nextLine();
                        if (val != "") {
                            Characteristic characteristic = charRepos.findByValue(val);
                            if (characteristic != null) {
                                characteristic.getSubjects().add(subject1);
                                subject1.getCharacteristics().add(characteristic);
                            } else {
                                System.out.println("Нету такой характеристики!");
                            }
                        }
                }
                subjectRepos.save(Objects.requireNonNull(subject1));
                break;

            case 6:
                System.out.println("Запустить систему.");

                Map<String, List<String>> objWithСharacteristics = subjectRepos.findAll().stream()
                        .collect(
                                Collectors.toMap
                                        (
                                                Subject::getValue,
                                                x -> x.getCharacteristics().stream()
                                                        .map(Characteristic::getValue).toList()
                                        )
                        );


                ProcessAnaliz process = new ProcessAnaliz(
                        charRepos.findAll().stream().map(Characteristic::getValue).toList()
                );
                process.process(objWithСharacteristics);
                break;

            default:
                break;
        }

    }
}
