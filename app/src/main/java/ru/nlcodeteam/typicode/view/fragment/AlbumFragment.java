package ru.nlcodeteam.typicode.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.TypicodeApplication;
import ru.nlcodeteam.typicode.Util;
import ru.nlcodeteam.typicode.adapter.AlbumsAdapter;
import ru.nlcodeteam.typicode.adapter.OnItemClickListener;
import ru.nlcodeteam.typicode.data.Mapper;
import ru.nlcodeteam.typicode.data.local.Album;
import ru.nlcodeteam.typicode.data.remote.AlbumModel;
import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.component.AlbumFragmentComponent;
import ru.nlcodeteam.typicode.di.component.DaggerAlbumFragmentComponent;
import ru.nlcodeteam.typicode.di.module.fragment.AlbumFragmentModule;
import ru.nlcodeteam.typicode.view.PhotoActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eldar on 28.10.2017.
 */

public class AlbumFragment extends Fragment implements OnItemClickListener{

    protected RecyclerView mRecyclerViewAlbums;
    private AlbumsAdapter mAdapter;
    private ArrayList<Album> mAlbums;
    int userId = -1;

    @Inject
    TypicodeService typicodeService;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_albums, container, false);
        mRecyclerViewAlbums = (RecyclerView) view.findViewById(R.id.recycler_view_albums);
        mAlbums = new ArrayList<>();
        userId = getArguments().getInt(Util.USER_ID);
        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        resolveDaggerDependency();

        Observable<List<AlbumModel>> request = typicodeService.getAlbumsByUser(userId);
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AlbumModel>>() {
                    @Override
                    public void onCompleted() {
                        showMessage("Loading complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                       showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<AlbumModel> albumModels) {
                        mAlbums = Mapper.getAlbums(albumModels);
                        refreshData();
                    }
                });

    }

    private void resolveDaggerDependency() {
        AlbumFragmentComponent component = DaggerAlbumFragmentComponent.builder()
                .albumFragmentModule(new AlbumFragmentModule(this))
                .applicationComponent(TypicodeApplication.get(getActivity()).component())
                .build();

        component.injectAlbumFragment(this);
    }
    @Override
    public void onItemClick(Object object, int position) {
        Album album = (Album) object;
        Intent intent = new Intent(getActivity(), PhotoActivity.class);
        intent.putExtra(Util.ALBUM_ID,album.getId());
        startActivity(intent);
    }

    private void refreshData() {
        mAdapter = new AlbumsAdapter(mAlbums,this);
        mRecyclerViewAlbums.setHasFixedSize(true);
        mRecyclerViewAlbums.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewAlbums.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerViewAlbums.setAdapter(mAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
