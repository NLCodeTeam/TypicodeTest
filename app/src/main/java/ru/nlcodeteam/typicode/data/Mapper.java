package ru.nlcodeteam.typicode.data;

import java.util.ArrayList;
import java.util.List;

import ru.nlcodeteam.typicode.data.local.Album;
import ru.nlcodeteam.typicode.data.local.Photo;
import ru.nlcodeteam.typicode.data.local.Post;
import ru.nlcodeteam.typicode.data.local.User;
import ru.nlcodeteam.typicode.data.remote.AlbumModel;
import ru.nlcodeteam.typicode.data.remote.PhotoModel;
import ru.nlcodeteam.typicode.data.remote.PostModel;
import ru.nlcodeteam.typicode.data.remote.UserModel;

/**
 * Created by eldar on 29.10.2017.
 */

public class Mapper {

    public static ArrayList<User> getUsers(List<UserModel> userModels) {
        ArrayList<User> users = new ArrayList<>();
        for (UserModel user : userModels) {
            users.add(new User(user.id,user.getMappingItems()));
        }

        return users;
    }

    public static ArrayList<Album> getAlbums(List<AlbumModel> albumModels) {
        ArrayList<Album> albums = new ArrayList<>();
        for (AlbumModel album : albumModels) {
            albums.add(new Album(album.id,album.userId,album.title));
        }

        return albums;
    }

    public static ArrayList<Post> getPosts(List<PostModel> postModels) {
        ArrayList<Post> posts = new ArrayList<>();
        for (PostModel post : postModels) {
            posts.add(new Post(post.id, post.userId, post.title,post.body));
        }

        return posts;
    }

    public static ArrayList<Photo> getPhotos(List<PhotoModel> photoModels) {
        ArrayList<Photo> photos = new ArrayList<>();
        for (PhotoModel photo : photoModels) {
            photos.add(new Photo(photo.title,photo.thumbnailUrl));
        }

        return photos;
    }


}
