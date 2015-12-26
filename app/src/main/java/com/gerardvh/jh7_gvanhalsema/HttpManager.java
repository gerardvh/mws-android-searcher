package com.gerardvh.jh7_gvanhalsema;

import android.util.Base64;
import android.util.Log;

import com.gerardvh.jh7_gvanhalsema.SLConstants.APIAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gerardvh on 3/30/15.
 */
public class HttpManager {

    public static String getData(RequestPackage p) {

        BufferedReader reader = null;
        String uri = p.getUri();
        if (p.getMethod().equals("GET")) {
            uri += "?" + p.getEncodedParams();
        }

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int responseCode = con.getResponseCode();
            Log.d("Mine", "Response Code: " + responseCode);

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

    public static String getSLData(RequestPackage p) {

        BufferedReader reader = null;
        String uri = p.getUri();
        if (p.getMethod().equals("GET")) {
            uri += "?" + p.getEncodedParams();
        }

        byte[] loginBytes = (APIAuth.SL_USERNAME + ":" + APIAuth.SL_PASSWORD).getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        try {
            URL url = new URL(uri);

            Log.d("Mine", "About to create HttpURLConnection");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(p.getMethod()); // use our custom class to get the method to use
            con.setChunkedStreamingMode(50);
            con.addRequestProperty("Authorization", loginBuilder.toString());

            StringBuilder sb = new StringBuilder();
            Log.d("Mine", "About to create BufferedReader for HttpStream");
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Log.d("Mine", "About to read from HTTP InputStream");
            String line;
//            int debug = 0;
            while ((line = reader.readLine()) != null) {
//                if (debug == 0) {
//                    Log.d("Mine", "Reading first line from stream");
//                } else if (debug == 500) {
//                    Log.d("Mine", "Reading 500'th line");
//                } else if (debug == 999) {
//                    Log.d("Mine", "Reading line 999");
//                }

                sb.append(line).append("\n");
//                debug++;
            }


            Log.d("Mine", "Finished reading from InputStream, returning HTTP response");
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }
}
