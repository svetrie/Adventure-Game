package com.example;

import com.google.gson.Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileLoader {

    /**
     * Accesses JSON file using URL and parses it using gson.
     * @param url is the URL that contains the JSON file
     * @return GameWorld object that represents the layout found in the JSON file
     */
    public static GameWorld parseJsonFileUsingUrl(String url) {
        final HttpResponse<String> stringHttpResponse;
        GameWorld gameWorld = null;
        Gson gson = new Gson();

        try {
            new URL(url);

            stringHttpResponse = Unirest.get(url).asString();
            String jsonString = stringHttpResponse.getBody();

            gameWorld = gson.fromJson(jsonString, GameWorld.class);

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Network not responding");
        }

        return gameWorld;
    }

    /**
     * Accesses JSON file using URL and parses it using gson.
     * @param filename is the name of the JSON file
     * @return GameWorld object that represents the layout found in the JSON file
     */
    public static GameWorld parseJsonFileUsingFilePath(String filename) {
        GameWorld gameWorld = null;
        Gson gson = new Gson();

        final Path path = FileSystems.getDefault().getPath("src/com/example/" + filename);

        try {
            String jsonString = new String(Files.readAllBytes(path));
            gameWorld = gson.fromJson(jsonString, GameWorld.class);
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + filename);
        }

        return gameWorld;
    }

}
