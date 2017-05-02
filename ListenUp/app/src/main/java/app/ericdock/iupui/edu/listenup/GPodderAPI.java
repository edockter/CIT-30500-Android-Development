package app.ericdock.iupui.edu.listenup;

import java.util.List;

import app.ericdock.iupui.edu.listenup.model.Podcast;
import app.ericdock.iupui.edu.listenup.model.Tag;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ericd on 4/3/2017.
 */

public interface GPodderAPI {
    String BASE_URL = "http://gpodder.net";

    @GET("api/2/tags/{quantity}.json")
    Call<Tag[]> getTopTags(@Path("quantity") String quantity);

    @GET("api/2/tag/{tag}/{quantity}.json")
    Call<Podcast[]> getTaggedPodcasts(@Path("quantity") String quantity, @Path("tag") String tag);

    @GET("api/2/data/podcast.json?url={url}")
    Call<Podcast> getPodcast(@Path("url") String url);

    @GET("api/2/devices/{username}.json")
    Call<String> getDevices(@Path("username") String username);

    // Login
    @POST("/api/2/auth/{username}/login.json")
        //void login(@Path("user") String user, @Header("Authorization") String authorization, Callback<String> callback);
    Call<ResponseBody> login(@Path("username") String user);

    // Get Subscriptions of Device
    @GET("/subscriptions/{username}/{deviceid}.json")
    Call<String[]> getDeviceSubscriptions(@Path("username") String user, @Path("deviceid") String device);

    // Get Subscriptions
    @GET("/subscriptions/{username}.json")
    Call<List<Subscription>> getSubscriptions(@Path("username") String user);

    // Upload Subscriptions of Device
    @PUT("/subscriptions/{username}/{deviceid}.json")
    Call<ResponseBody> uploadDeviceSubscriptions(@Body List<String> subscriptions, @Path("username") String user, @Path("deviceid") String device);

    // Upload Subscription Changes
    @POST("/api/2/subscriptions/{username}/{deviceid}.json")
    Call<UpdatedUrls> uploadDeviceSubscriptionsChanges(@Body SubscriptionChanges subscriptionChanges, @Path("username") String user, @Path("deviceid") String device);

}