package com.example.m8_uf2_pac1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Song> {

    public ListAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    private static class ViewHolder {
        ImageView imageSongPCP;
        TextView nameSongPCP;
        TextView timeSongPCP;
        Button startButtonPCP;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Song songPCP = getItem(position);
        ViewHolder viewHolderPCP;

        if (convertView != null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_adapter, parent, false);

            viewHolderPCP = new ViewHolder();
            viewHolderPCP.imageSongPCP = (ImageView)convertView.findViewById(R.id.songPhoto);
            viewHolderPCP.nameSongPCP = (TextView) convertView.findViewById(R.id.songName);
            viewHolderPCP.timeSongPCP = (TextView)convertView.findViewById(R.id.songTime);

            convertView.setTag(viewHolderPCP);
        } else {
            viewHolderPCP = (ViewHolder) convertView.getTag();
        }

        if (songPCP != null) {
            viewHolderPCP.nameSongPCP.setText(songPCP.namePCP);
            viewHolderPCP.timeSongPCP.setText(songPCP.timePCP);
        }

        viewHolderPCP.startButtonPCP.setOnClickListener(view -> {

        });

        return convertView;
    }
}
