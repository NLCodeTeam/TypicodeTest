package ru.nlcodeteam.typicode.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.TypicodeApplication;
import ru.nlcodeteam.typicode.Util;
import ru.nlcodeteam.typicode.adapter.PhotosAdapter;
import ru.nlcodeteam.typicode.data.Mapper;
import ru.nlcodeteam.typicode.data.local.Photo;
import ru.nlcodeteam.typicode.data.remote.PhotoModel;
import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.component.DaggerPhotoActivityComponent;
import ru.nlcodeteam.typicode.di.component.PhotoActivityComponent;
import ru.nlcodeteam.typicode.di.module.activity.PhotoActivityModule;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PhotoActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    protected ListView mListView;
    private PhotosAdapter mAdapter;
    private ArrayList<Photo> mPhotos;

    @Inject
    TypicodeService typicodeService;

    private int albumId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        mPhotos = new ArrayList<>();
        albumId = getIntent().getIntExtra(Util.ALBUM_ID,-1);
        resolveDaggerDependency();
    }


    private void resolveDaggerDependency() {
        PhotoActivityComponent component = DaggerPhotoActivityComponent.builder()
                .photoActivityModule(new PhotoActivityModule(this))
                .applicationComponent(TypicodeApplication.get(this).component())
                .build();

        component.injectPhotoActivity(this);
        loadPhotos();
    }

    private void loadPhotos() {

        Observable<List<PhotoModel>> request = typicodeService.getPhotosByAlbum(albumId);
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PhotoModel>>() {
                    @Override
                    public void onCompleted() {
                        showMessage("Loading complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<PhotoModel> photoModels) {
                        mPhotos = Mapper.getPhotos(photoModels);
                        refreshData();
                    }
                });

    }


    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void refreshData() {
        mAdapter = new PhotosAdapter(getApplicationContext(),mPhotos);
        mListView.setAdapter(mAdapter);
    }
}
