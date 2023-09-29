package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;

        String myURL = "https://api.zippopotam.us/us/33162";

        try {
            url = new URL(myURL);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong with the URL");
        }

        // Connection

        try {
            if (url != null) {
                connection = (HttpURLConnection) url.openConnection();
                responseCode = connection.getResponseCode();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (responseCode == 200) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder apiData = new StringBuilder();
                String readLine = null;

                while (true) {
                    try {
                        if ((readLine = bufferedReader.readLine()) == null) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    apiData.append(readLine);
                }

                JSONObject jsonAPIResponse = new JSONObject(apiData.toString());
                System.out.println(jsonAPIResponse);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Api call is not successful");
        }

    }
}