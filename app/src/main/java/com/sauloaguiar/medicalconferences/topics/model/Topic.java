package com.sauloaguiar.medicalconferences.topics.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by sauloaguiar on 2/11/17.
 */

@RealmClass
public class Topic extends RealmObject {

    @PrimaryKey
    private String id;

    @Required
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
