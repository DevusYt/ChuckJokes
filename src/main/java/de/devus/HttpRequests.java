package de.devus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpRequests {

    public static String randomJokeNoCategory() throws IOException {
        String urlString = "https://api.chucknorris.io/jokes/random";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JsonObject jsonObject = new Gson().fromJson(content.toString(), JsonObject.class);

        return ">>> " + jsonObject.get("value").getAsString();
    }
    
    public static List<String> multipleJokesNoCategory(int amount) throws IOException {

        List<String> jokes = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            String urlString = "https://api.chucknorris.io/jokes/random";

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JsonObject jsonObject = new Gson().fromJson(content.toString(), JsonObject.class);

            jokes.add(">>> " + jsonObject.get("value").getAsString());
        }

        return jokes;
    }

    public static String singleJokeWithCategory(String category) throws IOException {
        String urlString = "https://api.chucknorris.io/jokes/random?category=" + category;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JsonObject jsonObject = new Gson().fromJson(content.toString(), JsonObject.class);

        return ">>> "  + jsonObject.get("value").getAsString();
    }

    public static List<String> multipleJokesWithCategory(int amount, String category) throws IOException {
        List<String> jokes = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            String urlString = "https://api.chucknorris.io/jokes/random?category=" + category;

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JsonObject jsonObject = new Gson().fromJson(content.toString(), JsonObject.class);

            jokes.add(">>> " + jsonObject.get("value").getAsString());
        }

        return jokes;
    }

}
