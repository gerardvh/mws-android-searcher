package com.gerardvh.jh7_gvanhalsema.parsers;

import com.gerardvh.jh7_gvanhalsema.model.Flower;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class FlowerXMLParser {

	public static List<Flower> parseFeed(String content) {
		
		try {
			
		    boolean inDataItemTag = false; //Currently reading an object?
		    String currentTagName = "";
		    Flower flower = null;
		    List<Flower> flowerList = new ArrayList<>();

		    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		    XmlPullParser parser = factory.newPullParser();
		    parser.setInput(new StringReader(content));

		    int eventType = parser.getEventType();

		    while (eventType != XmlPullParser.END_DOCUMENT) {
//                XmlPullParser generates these events and you can do stuff with each
//                look at the docs for more info

		        switch (eventType) {
		            case XmlPullParser.START_TAG:
		                currentTagName = parser.getName();
		                if (currentTagName.equals("product")) {
		                    inDataItemTag = true;
		                    flower = new Flower();
		                    flowerList.add(flower);
		                }
		                break;

		            case XmlPullParser.END_TAG:
		                if (parser.getName().equals("product")) {
		                    inDataItemTag = false;
		                }
		                currentTagName = "";
		                break;

		            case XmlPullParser.TEXT:
		                if (inDataItemTag && flower != null) {
		                    switch (currentTagName) {
		                        case "productId":
		                            flower.setProductID(Integer.parseInt(parser.getText()));
		                            break;
		                        case "name":
		                        	flower.setName(parser.getText());
		                        	break;
		                        case "instructions":
		                            flower.setInstructions(parser.getText());
		                            break;
		                        case "category":
		                            flower.setCategory(parser.getText());
		                            break;
		                        case "price" :
		                        	flower.setPrice(Double.parseDouble(parser.getText()));
		                            break;
		                        case "photo" :
		                        	flower.setPhoto(parser.getText());
		                        default:
		                            break;
		                    }
		                }
		                break;
		        }

		       eventType = parser.next();

		    }

		    return flowerList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

		
	}
	
}
