package com.sauloaguiar.medicalconferences.conferences.presenter;

import com.sauloaguiar.medicalconferences.conferences.model.Conference;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public interface IConferencePresenter {

    void start();
    void stop();
    void loadConferences();
    boolean saveConference(Conference conference);
    boolean cancelConference(Conference conference);

    void sendToTopicsActivity(Conference conference);

    void editConference(Conference conference);
}
