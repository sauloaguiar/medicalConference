package com.sauloaguiar.medicalconferences.conferences.presenter;

import com.sauloaguiar.medicalconferences.conferences.model.Conference;
import com.sauloaguiar.medicalconferences.conferences.model.ConferencesDataSource;
import com.sauloaguiar.medicalconferences.conferences.view.ConferenceEditView;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public class ConferenceEditPresenter implements IConferenceEditPresenter, ConferencesDataSource.GetConferenceCallback {

    private ConferencesDataSource dataSource;
    private ConferenceEditView view;
    private String conferenceId;

    public ConferenceEditPresenter(String conferenceId, ConferenceEditView view, ConferencesDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
        this.conferenceId = conferenceId;
    }

    @Override
    public void start() {
        dataSource.loadConference(conferenceId, this);
    }

    @Override
    public void update(Conference conference) {

    }

    @Override
    public void stop() {
        this.view = null;
        this.dataSource = null;
        this.conferenceId = null;
    }

    @Override
    public void onConferenceLoaded(Conference conference) {
        view.displayConferenceData(conference);
    }

    @Override
    public void onDataNotAvailable() {

    }
}
