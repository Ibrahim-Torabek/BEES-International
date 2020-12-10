package ibrahim.example.beesinernatinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


/**
 * <h1>App for BEES International</h1>
 * <h2>Android Project of MAD305 Course</h2>
 * <p>This class is settings activity that extends AppCompactActivity.</p>
 * <p>
 *
 * @author  Wusiman Yibulayin (Ibrahim)
 * @version 1.0
 * @since   2020-11-20
 *
 * @see AppCompatActivity
 *
 */
public class SettingsActivity extends AppCompatActivity {
    public static int textSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MySettingsFragment())
                .commit();
    }

    public static class MySettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        System.out.println("User Left ...");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int textSize = Integer.parseInt(preferences.getString("settings_text_size", "20"));
        System.out.println(textSize);
    }


}