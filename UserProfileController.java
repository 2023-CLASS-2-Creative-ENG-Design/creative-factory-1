package com.example.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RestController
public class UserProfileController {

    private String arduinoData = "";  // 아두이노에서 데이터를 저장할 변수

    @GetMapping("/getData")
    public String getData() {
        return arduinoData;  // 클라이언트에게 데이터 제공
    }

    @PostMapping("/updateData")
    public void updateData(@RequestBody String newData) {
        arduinoData += newData + "\n";  // 새로운 데이터를 다음 줄에 추가, 아두이노에서 데이터 업데이트
    }

    @Scheduled(fixedRate = 5000) // 5초마다 이 메서드 실행 (필요에 따라 조정)
    public void fetchDataFromArduino() {
        // 아두이노에서 데이터를 가져와서 arduinoData를 업데이트하는 로직을 여기에 구현

        // 아두이노에서 가져온 값을 가정하고 예시로 출력
        double measurementValue = getMeasurementValueFromArduino();

        // 현재 시간을 가져오기
        LocalDateTime currentTime = LocalDateTime.now();

        // 시간을 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        // arduinoData에 현재 시간과 값을 추가
        arduinoData = "[" + formattedTime + "] : " + measurementValue;
    }

    private double getMeasurementValueFromArduino() {
        // 여기에 아두이노에서 값을 가져오는 로직을 구현
        // 아래는 예시로 1.0을 반환하는 코드
        return 1.0;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserProfileController.class, args);
    }
}

