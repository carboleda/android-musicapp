package co.devhack.musicapp.presetation.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.devhack.musicapp.R;
import co.devhack.musicapp.presetation.contract.TopTracksContract;
import co.devhack.musicapp.presetation.presenter.TopTracksPresenter;
import co.devhack.musicapp.presetation.view.adapter.TopTracksAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopTracksFragment extends Fragment implements TopTracksContract.View {

    private TopTracksContract.Presenter presenter;
    private RecyclerView rvTopTracks;

    public TopTracksFragment() {
        // Required empty public constructor
    }

    public static TopTracksFragment getInstance() {
        return new TopTracksFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_tracks, container, false);

        //La obtencion de las referencias del layout debe hacerse en este metodo
        //en lugar del onCreate, ya que aqui se infla el layout y el onCreate se
        //ejecuta antes que el onCreateView
        if (view != null) {
            presenter = new TopTracksPresenter(this);

            rvTopTracks = view.findViewById(R.id.rvTopTracks);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvTopTracks.setLayoutManager(layoutManager);

            TopTracksAdapter topTracksAdapter = new TopTracksAdapter(presenter.getTracks());
            rvTopTracks.setAdapter(topTracksAdapter);

            presenter.loadTracks();
        }

        return view;
    }

    @Override
    public void showLoadingIndicator() {
        //TODO IMPLEMENTAR INDICATOR
    }

    @Override
    public void hideLoadingIndicator() {
        //TODO IMPLEMENTAR INDICATOR
    }

    @Override
    public void refreshTracks() {
        //Fuerza que se refresque la lista de items del RecyclreView
        rvTopTracks.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoginErrorMessage(Throwable error) {
        //TODO MOSTRAR ERROR
    }
}
