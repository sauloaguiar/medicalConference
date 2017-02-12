package com.sauloaguiar.medicalconferences.conferences.view;

import com.sauloaguiar.medicalconferences.conferences.model.Conference;

import java.util.List;

/**
 * Created by sauloaguiar on 2/11/17.
 */
public interface ConferenceView {

    void onConferenceListLoaded(List<Conference> conferences);
    void onConferenceLoaded(Conference conference);

    void showLoading();
    void hideLoading();

    void redirectToTopics(Conference conference);

    void showEditConference(Conference conference);
}
