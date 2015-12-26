package com.gerardvh.jh7_gvanhalsema.model;

/**
 * Created by gerardvh on 4/1/15.
 */
public class Computer {
    String serialNumber;
    String assetTag;
    String macAddress;
    String assignedTo;
    String location;
    String comments;
    String id;

//    public Computer(String serialNumber, String assetTag, String macAddress, String assignedTo, String location) {
//        this.serialNumber = serialNumber;
//        this.assetTag = assetTag;
//        this.macAddress = macAddress;
//        this.assignedTo = assignedTo;
//        this.location = location;
//    }

    public Computer() {
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
//        return "Computer{" +
//                "assetTag='" + assetTag + '\'' +
//                "serialNumber='" + serialNumber + '\'' +
//                '}' + '\n';
        if (assetTag != "") {
            return assetTag;
        } else {
            return serialNumber;
        }
    }


    public String toJSONString() {
        return "Computer{" +
                "serialNumber='" + serialNumber + '\'' +
                ", assetTag='" + assetTag + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
