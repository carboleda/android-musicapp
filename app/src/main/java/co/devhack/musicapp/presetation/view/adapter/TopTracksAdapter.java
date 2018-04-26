package co.devhack.musicapp.presetation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.devhack.musicapp.R;
import co.devhack.musicapp.domain.model.Image;
import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.helpers.LastFmUtilities;

/**
 * Created by krlosf on 23/04/18.
 */

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.TrackViewHolder> {

    private List<Track> dataSet;

    public TopTracksAdapter(List<Track> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_track_item, parent, false);
        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = dataSet.get(position);

        Image image = LastFmUtilities.getImageBySize(track.getImages(), LastFmUtilities.ImageSize.MEDIUM);
        if(image != null) {
            Glide.with(holder.itemView.getContext())
                    .load(image.getUrl())
                    .into(holder.imgTrack);
        }
        holder.tvTrackName.setText(track.getName());
        holder.tvArtistName.setText(track.getArtist().getName());
        holder.tvDuration.setText(String.valueOf((double)(track.getDuration()/60.0F)));
        holder.tvListeners.setText(String.valueOf(track.getListeners()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTrack;
        TextView tvTrackName;
        TextView tvArtistName;
        TextView tvDuration;
        ImageView btnLove;
        TextView tvListeners;

        public TrackViewHolder(View itemView) {
            super(itemView);

            imgTrack = itemView.findViewById(R.id.imgTrack);
            tvTrackName = itemView.findViewById(R.id.tvTrackName);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            btnLove = itemView.findViewById(R.id.btnLove);
            tvListeners = itemView.findViewById(R.id.tvListeners);
        }
    }

}
