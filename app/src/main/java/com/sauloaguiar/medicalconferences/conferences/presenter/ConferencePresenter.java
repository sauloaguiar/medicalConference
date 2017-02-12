package com.sauloaguiar.medicalconferences.conferences.presenter;

import com.sauloaguiar.medicalconferences.conferences.view.ConferenceView;
import com.sauloaguiar.medicalconferences.conferences.model.Conference;
import com.sauloaguiar.medicalconferences.conferences.model.ConferencesDataSource;

import java.util.List;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public class ConferencePresenter implements IConferencePresenter, ConferencesDataSource.LoadConferencesCallback {

    private ConferenceView view;
    private ConferencesDataSource api;

    public ConferencePresenter(ConferenceView view, ConferencesDataSource api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void start() {
        loadConferences();
    }

    @Override
    public void stop() {
        this.view = null;
        this.api = null;
    }

    public void loadConferences() {
        view.showLoading();
        api.loadConferences(this);
    }

    @Override
    public boolean saveConference(Conference conference) {
        return false;
    }

    @Override
    public boolean cancelConference(Conference conference) {
        return false;
    }

    @Override
    public void sendToTopicsActivity(Conference conference) {
        view.redirectToTopics(conference);
    }

    @Override
    public void editConference(Conference conference) {
        view.showEditConference(conference);
    }

    @Override
    public void onConferencesLoaded(List<Conference> conferences) {
        view.hideLoading();
        view.onConferenceListLoaded(conferences);
    }

    @Override
    public void onDataNotAvailable() {

    }
}
