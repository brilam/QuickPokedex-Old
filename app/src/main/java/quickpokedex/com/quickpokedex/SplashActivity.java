package quickpokedex.com.quickpokedex;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Calls for the Pokedex Activity after 1s
        Intent intent = new Intent(this, PokedexActivity.class);
        SystemClock.sleep(1000);
        startActivity(intent);
        finish();
    }
}
