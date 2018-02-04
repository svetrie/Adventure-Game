package com.example;

import com.google.gson.Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.net.URL;

public class JsonFileLoader {

    public static void getJsonFileUsingUrl(String url) throws UnirestException, MalformedURLException {
        final HttpResponse<String> stringHttpResponse;

        new URL(url);

        stringHttpResponse = Unirest.get(url).asString();
        String jsonString = stringHttpResponse.getBody();
        Gson gson = new Gson();
        final GameWorld gameWorld = gson.fromJson(jsonString, GameWorld.class);

        System.out.println(gameWorld.getStartingRoom());
        System.out.println(gameWorld.getEndingRoom());
    }

    public static void main(String args[]) {

        try {
            getJsonFileUsingUrl("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Network not responding");
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        }
    }
}
