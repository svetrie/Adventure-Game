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

    public static GameWorld parseJsonFileUsingFilePath(String filename) {
        GameWorld gameWorld = null;
        Gson gson = new Gson();

        final Path path = FileSystems.getDefault().getPath("test", filename);

        try {
            String jsonString = new String(Files.readAllBytes(path));
            gameWorld = gson.fromJson(jsonString, GameWorld.class);
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + filename);
            System.exit(-1);
        }

        return gameWorld;
    }

    public static void main(String args[]) {
        GameWorld g = parseJsonFileUsingUrl("HI");
        Room[] r = g.getRooms();
        for(String item: r[1].getItems()) {
            System.out.println(item);
        }
    }
}
