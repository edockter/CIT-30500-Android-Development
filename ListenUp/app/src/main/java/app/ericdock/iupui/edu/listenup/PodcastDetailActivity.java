package app.ericdock.iupui.edu.listenup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import app.ericdock.iupui.edu.listenup.model.Podcast;

/**
 * Created by ericd on 4/10/2017.
 */

public class PodcastDetailActivity extends SingleFragmentActivity {
    public static final String EXTRA_PODCAST_BUNDLE = "listenup_podcast_detail";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected Fragment createFragment() {
        return new PodcastDetailFragment();
    }

    public static Intent newIntent(Context packageContext, Podcast podcast) {
        Intent intent = new Intent(packageContext, PodcastDetailActivity.class);
        intent.putExtra(EXTRA_PODCAST_BUNDLE, podcast);
        return intent;
    }
}