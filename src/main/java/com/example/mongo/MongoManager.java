package com.example.mongo;

import com.example.bpmndemo.Config;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Properties;

public class MongoManager {
    private static MongoClient mongoClient;
    private static final Morphia morphia = new Morphia();
    private static Datastore datastore;
    private static String host;
    private static Integer port;
    private static String username;
    private static String password;
    private static String dbName;
    private static Integer maxWaitTime;
    static {
        //PropertiesUtil util = Config.properties;
        Properties properties = Config.properties;
        host = properties.getProperty("host");
        port = Integer.parseInt(properties.getProperty("port"));
        username=properties.getProperty("username");
        password=properties.getProperty("password");
        dbName=properties.getProperty("db-name");
        maxWaitTime=Integer.parseInt(properties.getProperty("max-wait-time"));
        initDataSource();
        Config.getInstance();
    }

    private MongoManager(){}

    private  static void initDataSource(){
        MongoCredential credential = MongoCredential.createScramSha1Credential(username,dbName,password.toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().maxWaitTime(maxWaitTime).build();
        mongoClient = new MongoClient(new ServerAddress(host,port),credential,options);
        datastore=morphia.createDatastore(mongoClient,dbName);
        datastore.ensureIndexes();
    }

    public static Datastore getDatastore() {
        if (null == datastore){
            initDataSource();
        }
        return datastore;
    }

    public static MongoDatabase getDatabase(){
        if(mongoClient==null){
            initDataSource();
        }
        return mongoClient.getDatabase(dbName);
    }

    public static MongoCollection<Document> getCollection(String collectionName){
        MongoDatabase mongoDatabase = getDatabase();
        return mongoDatabase.getCollection(collectionName);
    }
}
