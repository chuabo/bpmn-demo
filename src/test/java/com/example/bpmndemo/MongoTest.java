package com.example.bpmndemo;

import com.alibaba.fastjson.JSONObject;
import com.example.mongo.MongoManager;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
//@SpringBootApplication(scanBasePackages ="com")
//@EnableAutoConfiguration
public class MongoTest {
    public static void main(String[]args) {
        Datastore ds = MongoManager.getDatastore();
        //ds.save()
        //JSONObject jo = new JSONObject();
        //jo.put("", "");

        MongoCollection<Document> collection=MongoManager.getCollection("OFFER_ENTITY");
        //FindIterable<Document> it = collection.find(new BasicDBObject("_id",""))
        MongoCursor<Document> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            Document doc = cursor.next();
            String jo = doc.toJson();
            System.out.println(jo);
        }

    }
}
