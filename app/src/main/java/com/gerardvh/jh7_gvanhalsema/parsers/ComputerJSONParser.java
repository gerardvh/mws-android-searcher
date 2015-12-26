package com.gerardvh.jh7_gvanhalsema.parsers;

import android.util.Log;

import com.gerardvh.jh7_gvanhalsema.SLConstants.CIParameterConstants;
import com.gerardvh.jh7_gvanhalsema.model.Computer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardvh on 4/2/15.
 */
public class ComputerJSONParser {

    public static List<Computer> parseFeed(String content) {
//        Initially assuming that we are going to query the CI table
//        TODO: Allow for parsing additional tables
        try {
//        JSONArray turns a string formatted as JSON into a list of untyped objects
            JSONObject result = new JSONObject(content);
            JSONArray ar = new JSONArray(result.getString("result"));
            List<Computer> computerList = new ArrayList<>();
//        Array to hold our objects
            for (int i = 0; i < ar.length(); i++) {
//                JSONObject obj = ar.getJSONObject(i);
//                Flower flower = new Flower();
//
//
//                flower.setName(obj.getString("name"));
//                flower.setCategory(obj.getString("category"));
//                flower.setInstructions(obj.getString("instructions"));
//                flower.setPhoto(obj.getString("photo"));
//                flower.setPrice(obj.getDouble("price"));
//                flower.setProductID(obj.getInt("productId"));
//
//                flowerList.add(flower);
                JSONObject obj = ar.getJSONObject(i);
                Computer computer = new Computer();

                computer.setAssetTag(obj.getString(CIParameterConstants.ASSET_TAG));
                computer.setSerialNumber(obj.getString(CIParameterConstants.SERIAL_NUMBER));
                computer.setMacAddress(obj.getString(CIParameterConstants.MAC_ADDRESS));
                computer.setAssignedTo(obj.getString(CIParameterConstants.ASSIGNED_TO));
                computer.setLocation(obj.getString(CIParameterConstants.LOCATION));
//                Log.d("Mine", "Adding additional computer to list in JSON Parser");
                if ((!computer.getSerialNumber().equals("")) &&
                        (!computer.getAssetTag().equals(""))) {
                    computerList.add(computer); // Item has both a serial and asset tag
                }
            }
            Log.d("Mine", "computerList totally populated");
            return computerList;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Mine", "Exception in JSON parser");
            return null;
        }

    }
}
