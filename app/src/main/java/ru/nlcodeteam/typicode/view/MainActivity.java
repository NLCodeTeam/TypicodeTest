package ru.nlcodeteam.typicode.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.TypicodeApplication;
import ru.nlcodeteam.typicode.Util;
import ru.nlcodeteam.typicode.adapter.OnItemClickListener;
import ru.nlcodeteam.typicode.adapter.UserAdapter;
import ru.nlcodeteam.typicode.data.Mapper;
import ru.nlcodeteam.typicode.data.local.User;
import ru.nlcodeteam.typicode.data.remote.UserModel;
import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.component.DaggerMainActivityComponent;
import ru.nlcodeteam.typicode.di.component.MainActivityComponent;
import ru.nlcodeteam.typicode.di.module.activity.MainActivityModule;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.recycler_view_users)
    protected RecyclerView mRecyclerViewUsers;

    @Inject
    TypicodeService typicodeService;

    private UserAdapter mAdapter;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        ButterKnife.bind(this);

        resolveDaggerDependency();

         users = new ArrayList<>();
        loadUsers();
    }

    private void resolveDaggerDependency() {
        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .applicationComponent(TypicodeApplication.get(this).component())
                .build();

        component.injectMainActivity(this);
    }

    private void loadUsers() {

        Observable<List<UserModel>> request = typicodeService.getUsers();
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserModel>>() {
                    @Override
                    public void onCompleted() {
                        showMessage("Loading complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<UserModel> userModels) {
                       ArrayList<User> temp = Mapper.getUsers(userModels);
                       MainActivity.this.users.addAll(Arrays.asList(temp.toArray(new User[0])));
                       refreshData();
                    }
                });


    }


    @Override
    public void onItemClick(Object obj,int position) {
        User user = (User) obj;
        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra(Util.USER_ID,user);
        startActivity(intent);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void refreshData() {
        mRecyclerViewUsers.setHasFixedSize(true);
        mRecyclerViewUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new UserAdapter(users,this);
        mRecyclerViewUsers.setAdapter(mAdapter);
    }
}
