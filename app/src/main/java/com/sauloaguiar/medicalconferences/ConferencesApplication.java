package com.sauloaguiar.medicalconferences;

import android.app.Application;

import com.sauloaguiar.medicalconferences.conferences.model.Conference;
import com.sauloaguiar.medicalconferences.topics.model.Topic;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public class ConferencesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder().
                initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        // add topics
                        Topic topic = realm.createObject(Topic.class, UUID.randomUUID().toString());
                        topic.setName("Pathology");
                        Topic topic2 = realm.createObject(Topic.class, UUID.randomUUID().toString());
                        topic2.setName("Anaesthesiology");
                        Topic topic3 = realm.createObject(Topic.class, UUID.randomUUID().toString());
                        topic3.setName("Endocrinology");

                        // add Conferences
                        Conference conference = realm.createObject(Conference.class, UUID.randomUUID().toString());
                        conference.setName("2017 USA AMA Conference");
                        conference.setLocation("Boston/MA");
                        conference.setDate("1/12/2018");
                        conference.setTopics(new RealmList<Topic>(topic, topic2, topic3));

                        Conference conference2 = realm.createObject(Conference.class, UUID.randomUUID().toString());
                        conference2.setName("2017 USA Endocrinoconference");
                        conference2.setLocation("Chicago/IL");
                        conference2.setDate("5/10/2017");
                        conference2.setTopics(new RealmList<Topic>(topic3));
                    }
                }).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
