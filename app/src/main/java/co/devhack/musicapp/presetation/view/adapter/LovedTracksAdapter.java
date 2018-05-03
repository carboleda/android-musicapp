package co.devhack.musicapp.presetation.view.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.devhack.musicapp.R;
import co.devhack.musicapp.domain.model.Image;
import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.helpers.LastFmUtilities;
import co.devhack.musicapp.helpers.StringUtilities;

/**
 * Created by krlosf on 23/04/18.
 */

public class LovedTracksAdapter extends RecyclerView.Adapter<LovedTracksAdapter.TrackViewHolder> {

    public interface TrackListener {
        void onTrackUnlove(int position);
        void onTrackSelect(int position);
    }

    private List<Track> dataSet;
    private TrackListener trackListener;

    public LovedTracksAdapter(List<Track> dataSet, TrackListener trackListener) {
        this.dataSet = dataSet;
        this.trackListener = trackListener;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_track_item, parent, false);
        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = dataSet.get(position);

        Image image = LastFmUtilities.getImageBySize(track.getImages(), LastFmUtilities.ImageSize.LARGE);
        if(image != null) {
            Glide.with(holder.itemView.getContext())
                    .load(image.getUrl())
                    .into(holder.imgTrack);
        }
        holder.tvTrackName.setText(track.getName());
        holder.tvArtistName.setText(track.getArtist().getName());
        //Si al usuario ya le gusta la cancion cambiamos el icono del boton por el corazon rojo
        Context context = holder.itemView.getContext();
        holder.btnLove.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_red_24dp));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            tvDuration.setVisibility(View.INVISIBLE);
            tvListeners.setVisibility(View.INVISIBLE);

            itemView.setOnClickListener(this);
            btnLove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Obtenemos la posicion del item (track) sobre el cual se genero el evento
            int positionItem = getAdapterPosition();
            switch (view.getId()) {
                case R.id.btnLove:
                    trackListener.onTrackUnlove(positionItem);
                    break;
                default:
                    trackListener.onTrackSelect(positionItem);
                    break;
            }
        }
    }

}
