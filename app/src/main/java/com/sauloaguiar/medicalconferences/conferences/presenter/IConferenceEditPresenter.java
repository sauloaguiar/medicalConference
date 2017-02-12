package com.sauloaguiar.medicalconferences.conferences.presenter;

import com.sauloaguiar.medicalconferences.conferences.model.Conference;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public interface IConferenceEditPresenter {
    void start();
    void update(Conference conference);

    void stop();
}
