package com.example.demo.service;

import com.example.demo.entity.Characteristic;
import com.example.demo.entity.Subject;
import com.example.demo.repos.CharRepos;
import com.example.demo.repos.SubjectRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessAnaliz {

    Map<String, List<String>> objWithСharacteristicsInProcess;

    Callback callback = m -> objWithСharacteristicsInProcess = m;

    List<String> characteristics ;

    public ProcessAnaliz(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public void process(Map<String, List<String>> map) {
        Scanner scannerInt = new Scanner(System.in);

        objWithСharacteristicsInProcess = new HashMap<>(map);

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
