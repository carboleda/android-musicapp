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

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.TrackViewHolder> {

    public interface TrackListener {
        void onTrackLoveUnlove(int position);
        void onTrackSelect(int position);
    }

    private List<Track> dataSet;
    private TrackListener trackListener;
    private boolean displayAsList;
    private int layoutResource;

    public TopTracksAdapter(List<Track> dataSet, TrackListener trackListener) {
        this.dataSet = dataSet;
        this.trackListener = trackListener;
        this.displayAsList = true;
    }

    public void setDisplayAsList(boolean displayAsList) {
        this.displayAsList = displayAsList;
        layoutResource = displayAsList ? R.layout.view_track_item : R.layout.view_track_item_grid;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = dataSet.get(position);
        Context context = holder.itemView.getContext();

        String size = displayAsList ? LastFmUtilities.ImageSize.LARGE : LastFmUtilities.ImageSize.EXTRALARGE;
        Image image = LastFmUtilities.getImageBySize(track.getImages(), size);
        if(image != null) {
            Glide.with(holder.itemView.getContext())
                    .load(image.getUrl())
                    .into(holder.imgTrack);
        }
        holder.tvTrackName.setText(track.getName());
        holder.tvArtistName.setText(track.getArtist().getName());

        //string con datos variables
        String strMinutes = StringUtilities.formatSecondsToDuration(track.getDuration());
        holder.tvDuration.setText(context.getString(R.string.minutes, strMinutes));

        holder.tvListeners.setText(StringUtilities.numberToSuffix(track.getListeners()));
        //Si al usuario ya le gusta la cancion cambiamos el icono del boton por el corazon rojo
        if(track.isLove()) {
            holder.btnLove.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_red_24dp));
        } else {
            //En caso contratio se vuelve a poner el icono blanco para evitar error con la reutilizacion del holder
            holder.btnLove.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_red_24dp));
        }
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

            itemView.setOnClickListener(this);
            btnLove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Obtenemos la posicion del item (track) sobre el cual se genero el evento
            int positionItem = getAdapterPosition();
            switch (view.getId()) {
                case R.id.btnLove:
                    trackListener.onTrackLoveUnlove(positionItem);
                    break;
                default:
                    trackListener.onTrackSelect(positionItem);
                    break;
            }
        }
    }

}
