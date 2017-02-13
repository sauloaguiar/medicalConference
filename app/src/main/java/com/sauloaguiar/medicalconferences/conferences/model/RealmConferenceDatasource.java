package com.sauloaguiar.medicalconferences.conferences.model;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

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
        Conference conference = loadConferenceByID(conferenceId);
        callback.onConferenceLoaded(conference);
    }

    @Override
    public void updateConference(final Conference conference, UpdateConferenceCallback callback) {
        try {
            Realm realm = Realm.getDefaultInstance();
            final Conference c = loadConferenceByID(conference.getId());
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    c.setLocation(conference.getLocation());
                    c.setDate(conference.getDate());
                    c.setName(conference.getName());
                }
            });
            callback.onConferenceUpdated(true);
        } catch (RealmException e) {
            e.printStackTrace();
            callback.onConferenceUpdated(false);
        }

    }

    private Conference loadConferenceByID(String conferenceId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Conference> results = realm.where(Conference.class).beginGroup().equalTo("id", conferenceId).endGroup().findAll();
        return results.get(0);
    }

}
