package ru.nlcodeteam.typicode.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.TypicodeApplication;
import ru.nlcodeteam.typicode.Util;
import ru.nlcodeteam.typicode.adapter.SectionsPagerAdapter;
import ru.nlcodeteam.typicode.data.local.Post;
import ru.nlcodeteam.typicode.data.local.User;
import ru.nlcodeteam.typicode.data.remote.PostModel;
import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.component.DaggerDetailActivityComponent;
import ru.nlcodeteam.typicode.di.component.DetailActivityComponent;
import ru.nlcodeteam.typicode.di.module.activity.DetailActivityModule;
import ru.nlcodeteam.typicode.view.fragment.DefaultPageChange;

public class DetailActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int userId;
    private User user;

    @Inject
    TypicodeService typicodeService;
    private int requestCode = 512;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (User) getIntent().getSerializableExtra(Util.USER_ID);
        if (user != null) {
            userId = user.getId();
            toolbar.setTitle(user.getName());
        }


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),userId);


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), PostEditActivity.class),requestCode);
            }
        });

        mViewPager.addOnPageChangeListener(new DefaultPageChange(tabLayout,fab));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



        resolveDaggerDependency();
    }


    private void resolveDaggerDependency() {
        DetailActivityComponent component = DaggerDetailActivityComponent.builder()
                .detailActivityModule(new DetailActivityModule(this))
                .applicationComponent(TypicodeApplication.get(this).component())
                .build();

        component.injectDetailActivity(this);
    }





    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void refreshData() {
        //TODO refresh fragment
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == this.requestCode) {

            String title = data.getStringExtra(Util.TITLE);
            String content = data.getStringExtra(Util.POST);
            PostModel post = new PostModel();
            post.title = title;
            post.body = content;
            post.userId = userId;
            Call<PostModel> request = typicodeService.addPost(post);
            request.enqueue(new Callback<PostModel>() {
                @Override
                public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                    if (response.isSuccessful()) {
                        PostModel model = response.body();
                        Post post = new Post(model.id,model.userId,model.title,model.body);
                        Intent intent = new Intent(Util.ACTION_POST_ADDED);
                        intent.putExtra(Util.POST,post);
                        LocalBroadcastManager.getInstance(DetailActivity.this).sendBroadcast(intent);
                    }
                    else
                        showMessage("The post was not added");
                }

                @Override
                public void onFailure(Call<PostModel> call, Throwable t) {
                      showMessage(t.getMessage());
                }
            });

            /*int mode = data.getIntExtra(Util.MODE,-1);
            switch (mode) {
                case Util.ADD_POST:

                    break;
                case Util.EDIT_POST:

                    break;
            }*/
        }
    }
}
