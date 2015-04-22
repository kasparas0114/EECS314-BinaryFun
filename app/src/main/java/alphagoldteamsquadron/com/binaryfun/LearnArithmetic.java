package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


/**
 * Created by Drew Mitchell on 4/22/15.
 */
public class LearnArithmetic extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_learn_arithmetic);


        webView = (WebView) findViewById(R.id.activity_main_arithmetic_webview);
        webView.loadUrl("file:///android_asset/learn_arithmetic.html");

    }
}
