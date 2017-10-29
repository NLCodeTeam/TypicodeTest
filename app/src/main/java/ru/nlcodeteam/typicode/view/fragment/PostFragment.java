package ru.nlcodeteam.typicode.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
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
import ru.nlcodeteam.typicode.adapter.OnItemClickListener;
import ru.nlcodeteam.typicode.adapter.PostsAdapter;
import ru.nlcodeteam.typicode.data.Mapper;
import ru.nlcodeteam.typicode.data.local.Post;
import ru.nlcodeteam.typicode.data.remote.PostModel;
import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.component.DaggerPostFragmentComponent;
import ru.nlcodeteam.typicode.di.component.PostFragmentComponent;
import ru.nlcodeteam.typicode.di.module.fragment.PostFragmentModule;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eldar on 28.10.2017.
 */

public class PostFragment extends Fragment implements OnItemClickListener {
    protected RecyclerView mRecyclerViewPosts;
    private PostsAdapter mAdapter;
    private ArrayList<Post> mPosts;
    private final int requestCode = 412;
    private int userId = -1;
    @Inject
    TypicodeService typicodeService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_posts, container, false);
        mPosts = new ArrayList<>();
        mAdapter = new PostsAdapter(mPosts,this);
        mRecyclerViewPosts = (RecyclerView) view.findViewById(R.id.recycler_view_posts);
        userId = getArguments().getInt(Util.USER_ID);
        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        resolveDaggerDependency();

        Observable<List<PostModel>> request = typicodeService.getPostsByUser(userId);
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PostModel>>() {
                    @Override
                    public void onCompleted() {
                        showMessage("Loading complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<PostModel> postModels) {
                        mPosts = Mapper.getPosts(postModels);
                        refreshData();
                    }
                });
    }

    @Override
    public void onItemClick(Object object, int position) {
       /* TODO implements if need
       Post post = (Post) object;
       Intent intent = new Intent(getContext(), PostEditActivity.class);
       intent.putExtra(Util.POST,post);
       intent.putExtra(Util.MODE,Util.EDIT_POST);
       intent.putExtra(Util.POSITION,position);
       startActivityForResult(intent,requestCode);*/
    }




    private void resolveDaggerDependency() {
        PostFragmentComponent component = DaggerPostFragmentComponent.builder()
                .postFragmentModule(new PostFragmentModule(this))
                .applicationComponent(TypicodeApplication.get(getActivity()).component())
                .build();

        component.injectPostFragment(this);
    }


    private void refreshData() {
        mAdapter = new PostsAdapter(mPosts,this);
        mRecyclerViewPosts.setHasFixedSize(true);
        mRecyclerViewPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewPosts.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerViewPosts.setAdapter(mAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(refreshReciever);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(refreshReciever,new IntentFilter(Util.ACTION_POST_ADDED));

    }

    BroadcastReceiver refreshReciever =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Post post = (Post) intent.getSerializableExtra(Util.POST);
            mPosts.add(post);
            refreshData();
        }
    };
}
