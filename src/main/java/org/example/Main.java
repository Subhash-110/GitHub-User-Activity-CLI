package org.example;


import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);
    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String @NonNull ... args) {
        System.out.print("Enter GitHub Username:");
        String username = scanner.nextLine();
        try {
            fetchGitHubActivity(username);
        } catch (IOException e) {
            System.out.println("Failed to fetch activity: " + e.getMessage());
        }
    }

    private void fetchGitHubActivity(String username) throws IOException {
        URI uri = URI.create(GITHUB_API_URL + username + "/events");
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int status = connection.getResponseCode();
        if (status != 200) {
            System.out.println("Error: Unable to fetch activity. Status code: " + status);
            return;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        parseAndDisplayActivity(response.toString());
    }

    private void parseAndDisplayActivity(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode events = objectMapper.readTree(jsonResponse);

        if (!events.isArray() || events.isEmpty()) {
            System.out.println("No recent activity found.");
            return;
        }

        for (JsonNode event : events) {
            String type = event.get("type").asString();
            String repoName = event.get("repo").get("name").asString();

            switch (type) {
                case "PushEvent":
                    System.out.println("Pushed to " + repoName);
                    break;
                case "IssuesEvent":
                    System.out.println("Opened an issue in " + repoName);
                    break;
                case "WatchEvent":
                    System.out.println("Starred " + repoName);
                    break;
                default:
                    System.out.println("Performed " + type + " on " + repoName);
                    break;
            }
        }
    }
}