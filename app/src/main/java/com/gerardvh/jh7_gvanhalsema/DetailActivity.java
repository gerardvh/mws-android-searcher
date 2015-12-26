//package com.gerardvh.jh7_gvanhalsema;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
//import android.widget.TextView;
//
//import com.gerardvh.jh7_gvanhalsema.model.Computer;
//
///**
// * Created by gerardvh on 4/13/15.
// */
//public class DetailActivity extends ActionBarActivity {
//    TextView serialTextView;
//    TextView assetTextView;
//    TextView assignedToTextView;
//    TextView locationTextView;
//    TextView macAddressTextView;
//    TextView commentsTextView;
//
//    String serialNumber;
//    String assetTag;
//    String assignedTo;
//    String location;
//    String macAddress;
//    String comments;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Intent intent = getIntent();
//        Computer computer = (Computer) intent.getExtras().get("computer");
//
//        serialTextView = (TextView) findViewById(R.id.serialTextView);
//        assetTextView = (TextView) findViewById(R.id.assetTagTextView);
//        assignedToTextView = (TextView) findViewById(R.id.assignedToTextView);
//        locationTextView = (TextView) findViewById(R.id.locationTextView);
//        macAddressTextView = (TextView) findViewById(R.id.macAddressTextView);
//        commentsTextView = (TextView) findViewById(R.id.commentsTextView);
//
//        serialNumber = computer.getSerialNumber();
//        assetTag = computer.getAssetTag();
//        assignedTo = computer.getAssignedTo();
//        location = computer.getLocation();
//        macAddress = computer.getMacAddress();
//        comments = computer.getComments();
//
//        if (!serialNumber.equals("")) {
//            serialTextView.setText(serialNumber);
//        }
//        if (!assetTag.equals("")) {
//            assetTextView.setText(assetTag);
//        }
//        if (!assignedTo.equals("")) {
//            assignedToTextView.setText(assignedTo);
//        }
//        if (!location.equals("")) {
//            locationTextView.setText(location);
//        }
//        if (!macAddress.equals("")) {
//            macAddressTextView.setText(macAddress);
//        }
//        if (!comments.equals("")) {
//            commentsTextView.setText(comments);
//        }
//
//    }
//}
