package com.sauloaguiar.medicalconferences.conferences.model;

import java.util.List;

/**
 * Created by sauloaguiar on 2/11/17.
 */
public interface ConferencesDataSource {

    void loadConferences(LoadConferencesCallback callback);
    void loadConference(String conferenceId, GetConferenceCallback callback);

    void updateConference(Conference conference, UpdateConferenceCallback callback);

    interface LoadConferencesCallback {
        void onConferencesLoaded(List<Conference> conferences);
        void onDataNotAvailable();
    }

    interface GetConferenceCallback {
        void onConferenceLoaded(Conference conference);
        void onDataNotAvailable();
    }

    interface UpdateConferenceCallback {
        void onConferenceUpdated(boolean success);
    }

}
