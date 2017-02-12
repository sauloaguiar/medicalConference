package com.sauloaguiar.medicalconferences.conferences.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.sauloaguiar.medicalconferences.R;
import com.sauloaguiar.medicalconferences.conferences.model.Conference;
import com.sauloaguiar.medicalconferences.conferences.model.RealmConferenceDatasource;
import com.sauloaguiar.medicalconferences.conferences.presenter.ConferencePresenter;
import com.sauloaguiar.medicalconferences.conferences.presenter.IConferencePresenter;
import com.sauloaguiar.medicalconferences.topics.view.TopicsActivity;

import java.util.List;

public class ConferencesActivity extends AppCompatActivity implements ConferenceView, ConferencesAdapter.SelectionCallback {

    private static final String TAG = ConferencesActivity.class.getName();
    private IConferencePresenter presenter;
    private Dialog progressDialog;
    private ConferencesAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferences);
        recyclerView = (RecyclerView) findViewById(R.id.conferences);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
        recyclerView.setLayoutManager(layoutManager);

        presenter = new ConferencePresenter(this, new RealmConferenceDatasource());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
        presenter = null;
    }

    @Override
    public void onConferenceListLoaded(List<Conference> conferences) {
        for(Conference c : conferences) {
            Log.d(TAG, c.getName() + " and topics: " + c.getTopics());
        }
        adapter = new ConferencesAdapter(conferences, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onConferenceLoaded(Conference conference) {

    }

    @Override
    public void showLoading() {
        progressDialog = new Dialog(ConferencesActivity.this, android.R.style.Theme_Translucent);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void redirectToTopics(Conference conference) {
        Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
        intent.putExtra("conference_id", conference.getId());
        startActivity(intent);
    }

    @Override
    public void showEditConference(Conference conference) {
        Intent intent = new Intent(getApplicationContext(), ConferenceEditActivity.class);
        intent.putExtra("conference_id", conference.getId());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(Conference conference) {
        presenter.sendToTopicsActivity(conference);
    }

    @Override
    public void onItemLongPress(Conference conference) {
        presenter.editConference(conference);
    }
}
