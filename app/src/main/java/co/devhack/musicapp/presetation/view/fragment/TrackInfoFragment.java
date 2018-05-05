package co.devhack.musicapp.presetation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.devhack.musicapp.R;
import co.devhack.musicapp.domain.model.Image;
import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.helpers.LastFmUtilities;
import co.devhack.musicapp.helpers.StringUtilities;
import co.devhack.musicapp.presetation.contract.TrackInfoContract;
import co.devhack.musicapp.presetation.presenter.TrackInfoPresenter;
import co.devhack.musicapp.presetation.view.activity.MainActivity;

/**
 * Created by krlosf on 29/04/18.
 */

public class TrackInfoFragment extends Fragment implements TrackInfoContract.View, View.OnClickListener {

    public static final String ARG_ARTIST = "artist";
    public static final String ARG_TRACK = "track";

    private MainActivity mainActivity;
    private TrackInfoContract.Presenter presenter;
    private ProgressBar pbLoadingIndicador;
    private ImageView imgTrack;
    private TextView tvTrackName;
    private TextView tvArtistName;
    private TextView tvListeners;
    private TextView tvPlayCount;
    private TextView tvSummary;
    private ImageView btnLove;

    public static TrackInfoFragment getInstance(String artist, String track) {
        TrackInfoFragment fragment = new TrackInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ARTIST, artist);
        args.putString(ARG_TRACK, track);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmant_track_info, container, false);


        mainActivity = ((MainActivity)getActivity());
        mainActivity.setEnableBackButton(true);

        //La obtencion de las referencias del layout debe hacerse en este metodo
        //en lugar del onCreate, ya que aqui se infla el layout y el onCreate se
        //ejecuta antes que el onCreateView
        if (view != null) {
            presenter = new TrackInfoPresenter(this);

            pbLoadingIndicador = getActivity().findViewById(R.id.pbLoadingIndicador);
            imgTrack = view.findViewById(R.id.imgTrack);
            tvTrackName = view.findViewById(R.id.tvTrackName);
            tvArtistName = view.findViewById(R.id.tvArtistName);
            tvListeners = view.findViewById(R.id.tvListeners);
            tvPlayCount = view.findViewById(R.id.tvPlayCount);
            tvSummary = view.findViewById(R.id.tvSummary);
            btnLove = view.findViewById(R.id.btnLove);

            btnLove.setOnClickListener(this);

            if(getArguments() != null) {
                String artist = getArguments().getString(ARG_ARTIST);
                String track = getArguments().getString(ARG_TRACK);
                presenter.loadInfo(artist, track);

                //Se pone el nombre de la cancion como titulo
                mainActivity.getSupportActionBar().setTitle(track);
            }
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        mainActivity.setEnableBackButton(false);
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLove:
                presenter.onLoveUnlove();
                break;
        }
    }

    @Override
    public void showLoadingIndicator() {
        pbLoadingIndicador.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        pbLoadingIndicador.setVisibility(View.GONE);
    }

    @Override
    public void showInfo(Track track) {
        Context context = getContext();
        if(context != null) {
            Image image = LastFmUtilities.getImageBySize(track.getAlbum().getLstImages(),
                    LastFmUtilities.ImageSize.EXTRALARGE);
            if (image != null) {
                Glide.with(context)
                        .load(image.getUrl())
                        .into(imgTrack);
            }
            tvTrackName.setText(track.getName());
            tvArtistName.setText(track.getArtist().getName());
            tvListeners.setText(StringUtilities.numberToSuffix(track.getListeners()));
            tvPlayCount.setText(getString(R.string.play_counts, StringUtilities.numberToSuffix(track.getPlaycount())));
            if (track.getWiki() != null) {
                tvSummary.setText(Html.fromHtml(track.getWiki().getSummary()));
            }
            //Si al usuario ya le gusta la cancion cambiamos el icono del boton por el corazon rojo
            if (track.isLove()) {
                btnLove.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_red_24dp));
            } else {
                //En caso contratio se vuelve a poner el icono blanco
                btnLove.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_red_24dp));
            }
        }
    }

    @Override
    public void showLoginErrorMessage(Throwable error) {

    }
}
