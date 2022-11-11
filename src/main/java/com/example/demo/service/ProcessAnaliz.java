package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProcessAnaliz {


    Map<String, List<String>> objWithСharacteristicsInProcess;

    Callback callback = m -> objWithСharacteristicsInProcess = m;

    List<String> characteristics = List.of("Крылья", "Лапки", "Хвост", "Зубы",
            "Шерсть", "Шасси", "Винт", "Хуй", "жопа", "Нос", "Рот");

    public void process() {
        Scanner scannerInt = new Scanner(System.in);
        int command = scannerInt.nextInt();

        Map<String, List<String>> objWithСharacteristics = new HashMap<>();

        objWithСharacteristics.put("Птица", List.of("Крылья", "Лапки"));
        objWithСharacteristics.put("Пёс", List.of("Лапки", "Хвост", "Зубы", "Шерсть"));
        objWithСharacteristics.put("Самолет", List.of("Крылья", "Шасси", "Винт"));

        objWithСharacteristicsInProcess = new HashMap<>(objWithСharacteristics);

        redoListOf("");

        System.out.println("Запустить систему.");

        while (objWithСharacteristicsInProcess.size() != 1) {

            if (objWithСharacteristicsInProcess.size() == 0) {
                System.out.println("Нет таких тут!");
                return;
            }

            String str = characteristics.get(0);

            System.out.println(String.format("Имеет ли объект %s ?", str));
            int bool = scannerInt.nextInt();

            callback.callback(objWithСharacteristicsInProcess.entrySet().stream()
                    .filter(e -> (bool == 1) == e.getValue().contains(str))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

            redoListOf(str);

        }
        if (objWithСharacteristicsInProcess.size() == 1) {
            end();
        }
    }

    public void end() {
        objWithСharacteristicsInProcess.keySet().forEach(System.out::println);
    }

    public void redoListOf(String characteristic) {
        List<String> newCharacteristics = new ArrayList<>();
        objWithСharacteristicsInProcess.values().forEach(newCharacteristics::addAll);

        newCharacteristics.retainAll(characteristics);

        Set<String> set = new HashSet<>(newCharacteristics);
        set.remove(characteristic);
        characteristics = set.stream().toList();
    }

    @FunctionalInterface
    private interface Callback {
        void callback(Map<String, List<String>> map);
    }
}
