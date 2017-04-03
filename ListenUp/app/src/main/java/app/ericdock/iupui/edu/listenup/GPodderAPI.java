package app.ericdock.iupui.edu.listenup;

import app.ericdock.iupui.edu.listenup.model.Tag;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ericd on 4/3/2017.
 */

public interface GPodderAPI {
    String BASE_URL = "http://gpodder.net";

    @GET("api/2/tags/{quantity}.json")
    Call<Tag[]> getTopTags(@Path("quantity") String quantity);
}