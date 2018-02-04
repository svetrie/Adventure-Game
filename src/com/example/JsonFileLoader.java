package com.example;

import com.google.gson.Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.net.URL;

public class JsonFileLoader {

    public static GameWorld parseDefaultJsonFileUsingUrl(String url) {
        final HttpResponse<String> stringHttpResponse;
        GameWorld gameWorld = null;

        try {
            new URL(url);

            stringHttpResponse = Unirest.get(url).asString();
            String jsonString = stringHttpResponse.getBody();

            Gson gson = new Gson();
            gameWorld = gson.fromJson(jsonString, GameWorld.class);

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Network not responding");
        }

        return gameWorld;
    }

    public static GameWorld parseJsonFileUsingFilePath() {
        return null;
    }

    public static void main(String args[]) {
        GameWorld g = parseDefaultJsonFileUsingUrl("HI");
        Room[] r = g.getRooms();
        for(String item: r[1].getItems()) {
            System.out.println(item);
        }
    }
}
