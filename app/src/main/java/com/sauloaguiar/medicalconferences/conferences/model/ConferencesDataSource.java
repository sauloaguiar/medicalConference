package com.sauloaguiar.medicalconferences.conferences.model;

import com.sauloaguiar.medicalconferences.conferences.model.Conference;

import java.util.List;

/**
 * Created by sauloaguiar on 2/11/17.
 */
public interface ConferencesDataSource {

    void loadConferences(LoadConferencesCallback callback);
    void loadConference(String conferenceId, GetConferenceCallback callback);

    interface LoadConferencesCallback {
        void onConferencesLoaded(List<Conference> conferences);
        void onDataNotAvailable();
    }

    interface GetConferenceCallback {
        void onConferenceLoaded(Conference conference);
        void onDataNotAvailable();
    }

}
