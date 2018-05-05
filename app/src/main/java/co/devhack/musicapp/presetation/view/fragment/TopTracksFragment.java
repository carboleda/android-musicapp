package co.devhack.musicapp.presetation.view.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import co.devhack.musicapp.R;
import co.devhack.musicapp.helpers.LocationUtil;
import co.devhack.musicapp.presetation.contract.TopTracksContract;
import co.devhack.musicapp.presetation.presenter.TopTracksPresenter;
import co.devhack.musicapp.presetation.view.activity.MainActivity;
import co.devhack.musicapp.presetation.view.adapter.TopTracksAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopTracksFragment extends Fragment implements TopTracksContract.View,
        TopTracksAdapter.TrackListener {

    private static final int REQUEST_LOCATION = 123;
    private TopTracksContract.Presenter presenter;
    private RecyclerView rvTopTracks;
    private ProgressBar pbLoadingIndicador;
    private TopTracksAdapter topTracksAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

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

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.top_tracks);
        setHasOptionsMenu(true);

        //La obtencion de las referencias del layout debe hacerse en este metodo
        //en lugar del onCreate, ya que aqui se infla el layout y el onCreate se
        //ejecuta antes que el onCreateView
        if (view != null) {
            presenter = new TopTracksPresenter(this);

            pbLoadingIndicador = getActivity().findViewById(R.id.pbLoadingIndicador);
            rvTopTracks = view.findViewById(R.id.rvTopTracks);

            linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            gridLayoutManager = new GridLayoutManager(getContext(), 2);


            rvTopTracks.setLayoutManager(linearLayoutManager);

            topTracksAdapter = new TopTracksAdapter(presenter.getTracks(), this);
            topTracksAdapter.setDisplayAsList(true);
            rvTopTracks.setAdapter(topTracksAdapter);

            initLocation();

            presenter.loadTracks();
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.top_tracks_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_display:
                boolean isDisplayList = !item.isChecked();
                item.setIcon(isDisplayList
                        ? R.drawable.ic_view_list_black_24dp : R.drawable.ic_view_module_black_24dp);
                item.setChecked(isDisplayList);
                changeDisplay(isDisplayList);
                break;
        }
        return super.onOptionsItemSelected(item);
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
        rvTopTracks.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showTrackInfo(String artist, String track) {
        /*Cambiar de fragment usando la referencia de getFragmentManager() en cada fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, TrackInfoFragment.getInstance(artist, track))
                .addToBackStack(null)
                .commit();*/

        //Cambiar de fragment usando un metodo centra en el Activity
        MainActivity mainActivity = (MainActivity) getActivity();
        if(mainActivity != null) {
            mainActivity.replaceFragment(TrackInfoFragment.getInstance(artist, track), true);
        }
    }

    @Override
    public void showLoginErrorMessage(Throwable error) {
        //TODO MOSTRAR ERROR
    }

    @Override
    public void onTrackLoveUnlove(int position) {
        presenter.onLoveUnlove(position);
    }

    @Override
    public void onTrackSelect(int position) {
        presenter.onTrackSelect(position);
    }

    private void initLocation() {
        boolean result = new LocationUtil()
                .init(getContext());
        if(!result) {
            String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions, REQUEST_LOCATION);
        }
    }

    private void changeDisplay(boolean isDisplayList) {
        rvTopTracks.setLayoutManager(isDisplayList ? linearLayoutManager : gridLayoutManager);
        topTracksAdapter = new TopTracksAdapter(presenter.getTracks(), this);
        topTracksAdapter.setDisplayAsList(isDisplayList);
        rvTopTracks.setAdapter(topTracksAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_LOCATION) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initLocation();
            }
        }
    }
}
