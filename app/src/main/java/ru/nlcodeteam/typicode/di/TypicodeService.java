package ru.nlcodeteam.typicode.di;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.nlcodeteam.typicode.data.remote.AlbumModel;
import ru.nlcodeteam.typicode.data.remote.PhotoModel;
import ru.nlcodeteam.typicode.data.remote.PostModel;
import ru.nlcodeteam.typicode.data.remote.UserModel;
import rx.Observable;

/**
 * Created by eldar on 29.10.2017.
 */

public interface TypicodeService {

        @GET(TypicodeServicePath.USERS_URL)
        Observable<List<UserModel>> getUsers();

        @GET(TypicodeServicePath.ALBUMS_URL)
        Observable<List<AlbumModel>> getAlbumsByUser(@Path("userId") int userId);

        @GET(TypicodeServicePath.POSTS_URL)
        Observable<List<PostModel>> getPostsByUser(@Path("userId") int userId);

        @GET(TypicodeServicePath.PHOTOS_URL)
        Observable<List<PhotoModel>> getPhotosByAlbum(@Query("albumId")  int albumId);

        @POST("/posts")
        Call<PostModel> addPost(@Body PostModel post);
}
