package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


/**
 * Created by Drew Mitchell on 4/22/15.
 */
public class LearnLogic extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_learn_logic);

        webView = (WebView) findViewById(R.id.activity_main_logic_webview);
        webView.loadUrl("file:///android_asset/learn_logic.html");

    }
}
