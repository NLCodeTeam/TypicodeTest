package ru.nlcodeteam.typicode.di;

/**
 * Created by eldar on 29.10.2017.
 */

public class TypicodeServicePath {

    public final static String BASE_URL = "https://jsonplaceholder.typicode.com";



    public final static String USERS_URL=BASE_URL+"/users";

    public final static String POSTS_URL =USERS_URL+"/{userId}/posts";

    public final static String ALBUMS_URL=USERS_URL+"/{userId}/albums";

    public final static String PHOTOS_URL=BASE_URL+"/photos";


}
