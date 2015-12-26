package com.gerardvh.jh7_gvanhalsema.parsers;

import android.util.Log;

import com.gerardvh.jh7_gvanhalsema.model.Flower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardvh on 3/30/15.
 */
public class FlowerJSONParser {

    public static List<Flower> parseFeed(String content) {
        try {
//        JSONArray turns a string formatted as JSON into a list of untyped objects
            JSONArray ar = new JSONArray(content);
            List<Flower> flowerList = new ArrayList<>();
//        Array to hold our objects
            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                Flower flower = new Flower();


                flower.setName(obj.getString("name"));
                flower.setCategory(obj.getString("category"));
                flower.setInstructions(obj.getString("instructions"));
                flower.setPhoto(obj.getString("photo"));
                flower.setPrice(obj.getDouble("price"));
                flower.setProductID(obj.getInt("productId"));

                flowerList.add(flower);
            }
            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Mine", "Exception in JSON parser");
            return null;
        }

    }
}
