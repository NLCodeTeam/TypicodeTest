package ru.nlcodeteam.typicode.data.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eldar on 29.10.2017.
 */

public class PostModel {


        @SerializedName("userId")
        public int userId;
        @SerializedName("id")
        public int id;
        @SerializedName("title")
        public String title;
        @SerializedName("body")
        public String body;
    }


