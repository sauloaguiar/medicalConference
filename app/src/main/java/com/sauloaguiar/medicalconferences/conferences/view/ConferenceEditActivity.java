package com.sauloaguiar.medicalconferences.conferences.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    public void onConferenceUpdated(boolean success) {
        String message;
        if(success){
            message = "Conference successfully updated";
        } else {
            message = "Error when updating conference";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }

    public void update(View v) {
        String date = conferenceDate.getEditableText().toString();
        String location = conferenceLocation.getEditableText().toString();
        String name = conferenceName.getEditableText().toString();
        Conference c = new Conference();
        c.setLocation(location);
        c.setDate(date);
        c.setName(name);

        presenter.update(c);
    }
}
