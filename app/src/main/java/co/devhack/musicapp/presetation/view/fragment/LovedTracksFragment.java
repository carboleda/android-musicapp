package co.devhack.musicapp.presetation.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import co.devhack.musicapp.R;
import co.devhack.musicapp.presetation.contract.LovedTracksContract;
import co.devhack.musicapp.presetation.presenter.LovedTracksPresenter;
import co.devhack.musicapp.presetation.view.adapter.LovedTracksAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LovedTracksFragment extends Fragment implements LovedTracksContract.View,
        LovedTracksAdapter.TrackListener {

    private LovedTracksContract.Presenter presenter;
    private RecyclerView rvLovedTracks;
    private ProgressBar pbLoadingIndicador;

    public LovedTracksFragment() {
        // Required empty public constructor
    }

    public static LovedTracksFragment getInstance() {
        return new LovedTracksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loved_tracks, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.my_loved_tracks);

        //La obtencion de las referencias del layout debe hacerse en este metodo
        //en lugar del onCreate, ya que aqui se infla el layout y el onCreate se
        //ejecuta antes que el onCreateView
        if (view != null) {
            presenter = new LovedTracksPresenter(this);

            pbLoadingIndicador = getActivity().findViewById(R.id.pbLoadingIndicador);
            rvLovedTracks = view.findViewById(R.id.rvLovedTracks);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvLovedTracks.setLayoutManager(layoutManager);

            LovedTracksAdapter topTracksAdapter = new LovedTracksAdapter(presenter.getTracks(), this);
            rvLovedTracks.setAdapter(topTracksAdapter);

            presenter.loadTracks();
        }

        return view;
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
    public void refreshTracks() {
        //Fuerza que se refresque la lista de items del RecyclreView
        rvLovedTracks.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoginErrorMessage(Throwable error) {
        //TODO MOSTRAR ERROR
    }

    @Override
    public void onTrackUnlove(int position) {
        presenter.onUnlove(position);
    }

    @Override
    public void onTrackSelect(int position) {

    }
}
