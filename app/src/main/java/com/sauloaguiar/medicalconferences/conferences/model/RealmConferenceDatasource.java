package com.sauloaguiar.medicalconferences.conferences.model;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public class RealmConferenceDatasource implements ConferencesDataSource {

    @Override
    public void loadConferences(LoadConferencesCallback callback) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Conference> results = realm.where(Conference.class).findAll();
        callback.onConferencesLoaded(results);
    }

    @Override
    public void loadConference(String conferenceId, GetConferenceCallback callback) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Conference> results = realm.where(Conference.class).beginGroup().equalTo("id", conferenceId).endGroup().findAll();
        callback.onConferenceLoaded(results.get(0));
    }
}
