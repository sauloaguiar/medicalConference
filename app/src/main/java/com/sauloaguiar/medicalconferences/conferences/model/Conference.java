package com.sauloaguiar.medicalconferences.conferences.model;

import com.sauloaguiar.medicalconferences.topics.model.Topic;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by sauloaguiar on 2/11/17.
 */

@RealmClass
public class Conference extends RealmObject {

    @PrimaryKey
    private String id;

    @Required
    private String name;

    private String location;

    private String date;

    RealmList<Topic> topics;

    public Conference(){}

    public RealmList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(RealmList<Topic> topics) {
        this.topics = topics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
