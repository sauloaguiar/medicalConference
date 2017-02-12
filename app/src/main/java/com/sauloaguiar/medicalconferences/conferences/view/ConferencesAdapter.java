package com.sauloaguiar.medicalconferences.conferences.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sauloaguiar.medicalconferences.R;
import com.sauloaguiar.medicalconferences.conferences.model.Conference;

import java.util.List;

/**
 * Created by sauloaguiar on 2/11/17.
 */

public class ConferencesAdapter extends RecyclerView.Adapter<ConferencesAdapter.ViewHolder> {

    interface SelectionCallback {
        void onItemSelected(Conference conference);
        void onItemLongPress(Conference conference);
    }
    private List<Conference> conferences;
    private SelectionCallback callback;

    public ConferencesAdapter(List<Conference> conferences, SelectionCallback callback) {
        this.conferences = conferences;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConferencesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.conferece_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Conference conference = conferences.get(position);
        holder.name.setText(conference.getName());
        holder.location.setText(conference.getLocation());
        holder.date.setText(conference.getDate());
    }

    @Override
    public int getItemCount() {
        return conferences.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView location;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemSelected(conferences.get(getAdapterPosition()));
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    callback.onItemLongPress(conferences.get(getAdapterPosition()));
                    return true;
                }
            });
        }
    }
}
