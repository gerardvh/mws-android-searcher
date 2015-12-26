package com.gerardvh.jh7_gvanhalsema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;


public class ComputerDetailActivity extends ActionBarActivity {
    TextView serialTextView;
    TextView assetTextView;
    TextView assignedToTextView;
    TextView locationTextView;
    TextView macAddressTextView;
    TextView commentsTextView;

    String serialNumber;
    String assetTag;
    String assignedTo;
    String location;
    String macAddress;
    String comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_detail);

        Intent intent = getIntent();

        serialTextView = (TextView) findViewById(R.id.serialTextView);
        assetTextView = (TextView) findViewById(R.id.assetTagTextView);
        assignedToTextView = (TextView) findViewById(R.id.assignedToTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        macAddressTextView = (TextView) findViewById(R.id.macAddressTextView);
        commentsTextView = (TextView) findViewById(R.id.commentsTextView);

        serialNumber = intent.getStringExtra("serialNumber");
        assetTag = intent.getStringExtra("assetTag");
        assignedTo = intent.getStringExtra("assignedTo");
        location = intent.getStringExtra("location");
        macAddress = intent.getStringExtra("macAddress");
        comments = intent.getStringExtra("comments");

        try { // Ensures that we use the defaults if there is no data on each item
            if (serialNumber != null && !serialNumber.equals("")) {
                serialTextView.setText(serialNumber);
            }
            if (assetTag != null && !assetTag.equals("")) {
                assetTextView.setText(assetTag);
            }
            if (assignedTo != null && !assignedTo.equals("")) {
                assignedToTextView.setText(assignedTo);
            }
            if (location != null && !location.equals("")) {
                locationTextView.setText(location);
            }
            if (macAddress != null && !macAddress.equals("")) {
                macAddressTextView.setText(macAddress);
            }
            if (comments != null && !comments.equals("")) {
                commentsTextView.setText(comments);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Mine", "Serial Number: " + serialNumber +
            "Asset Tag: " + assetTag +
            "Assigned To: " + assignedTo +
            "Location: " + location +
            "MAC Address: " + macAddress +
            "Comments: " + comments);
        }

    }
}
