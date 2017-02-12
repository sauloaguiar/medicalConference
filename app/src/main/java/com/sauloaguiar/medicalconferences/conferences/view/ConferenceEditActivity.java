package com.sauloaguiar.medicalconferences.conferences.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.sauloaguiar.medicalconferences.R;
import com.sauloaguiar.medicalconferences.conferences.model.Conference;
import com.sauloaguiar.medicalconferences.conferences.model.RealmConferenceDatasource;
import com.sauloaguiar.medicalconferences.conferences.presenter.ConferenceEditPresenter;
import com.sauloaguiar.medicalconferences.conferences.presenter.IConferenceEditPresenter;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public class ConferenceEditActivity extends AppCompatActivity implements ConferenceEditView {

    private IConferenceEditPresenter presenter;
    private EditText conferenceName;
    private EditText conferenceLocation;
    private EditText conferenceDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_edit);
        setTitle(R.string.conference_edit_title);

        presenter = new ConferenceEditPresenter(
                getIntent().getStringExtra("conference_id"),
                this,
                new RealmConferenceDatasource());

        conferenceDate = (EditText) findViewById(R.id.date);
        conferenceLocation = (EditText) findViewById(R.id.location);
        conferenceName = (EditText) findViewById(R.id.name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void displayConferenceData(Conference conference) {
        conferenceDate.setText(conference.getDate());
        conferenceLocation.setText(conference.getLocation());
        conferenceName.setText(conference.getName());
    }
}
