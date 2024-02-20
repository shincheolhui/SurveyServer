package com.lec.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootTest
class SurveyServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void AlphaVantageApiRequestTest() {
        // Replace "YOUR_API_KEY" with your actual API key from Alpha Vantage
        String apiKey = "YOUR_API_KEY";
        String symbol = "AAPL"; // Replace with the desired stock symbol

        try {
            // Build the API URL
//            String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=1min&apikey=" + apiKey;
            String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=SPXL&interval=1min&month=2008-11&outputsize=full&apikey=MS1UL4HCRM6NIIJJ";

            System.out.println("빌드된 API URL 확인 : " + apiUrl);

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful (HTTP 200 OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Print the response
                System.out.println("response data 의 toString() : " + response.toString());

            } else {
                System.out.println("Error: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void jsonJavaParsing() {
        // 예제 JSON 문자열
        String jsonString = "{\"name\":\"John Doe\",\"age\":30,\"city\":\"New York\"}";

        // Jackson ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON 문자열을 Java 객체로 변환
            Person person = objectMapper.readValue(jsonString, Person.class);

            System.out.println(person);

            // 변환된 객체 출력
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
            System.out.println("City: " + person.getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
