package com.vulps.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RequestManager {
    private static final String BASE_URL = "https://vortexevaderbackend.tx296rt2wp.repl.co";

    private static final Gson gson = new GsonBuilder().create();

    public static void addPlaying() {
        String endpoint = BASE_URL + "/launch";

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle the successful response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();
                reader.close();
                System.out.println("Response: " + response);
            } else {
                // Handle the error response
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPlays() {
        String endpoint = BASE_URL + "/plays";

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle the successful response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Extract the play count from the response
                return gson.fromJson(response.toString(), PlayCountResponse.class).getPlayCount();
            } else {
                // Handle the error response
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void submitScore(String player, int score) {
        String endpoint = BASE_URL + "/submit-score";

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String requestBody = "player=" + player + "&score=" + score;
            connection.getOutputStream().write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle the successful response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();
                reader.close();
                System.out.println("Response: " + response);
            } else {
                // Handle the error response
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LeaderboardEntry[] getLeaderboard() {
        String endpoint = BASE_URL + "/leaderboard";

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle the successful response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Convert the JSON response to an array of LeaderboardEntry objects
                LeaderboardEntry[] leaderboard = gson.fromJson(response.toString(), LeaderboardEntry[].class);
                return leaderboard;
            } else {
                // Handle the error response
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new LeaderboardEntry[0];
    }

    public record LeaderboardEntry(String player, int score) {
        @Override
            public String toString() {
                return "Player: " + player + ", Score: " + score;
            }
        }

    private static class PlayCountResponse {
        private int play_count;

        public PlayCountResponse(int play_count){
            this.play_count = play_count;
        }

        public int getPlayCount() {
            return play_count;
        }
    }
}

