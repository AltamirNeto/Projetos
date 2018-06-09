package com.example.neto_.lojavirtual;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//classe para enviar e receber o json do servidor

public class WebClient {
    public String post(String json){
        try {
            URL url = new URL("http://www.servidor.com");       //URL do servidor para enviar os dados em forma de Json
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");      //apenas envia arquivo do tipo application/json
            connection.setRequestProperty("Accept", "application/json");            //apenas recebe arquivo do tipo application/json

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String response = scanner.next();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
