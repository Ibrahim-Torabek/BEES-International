package ibrahim.example.beesinernatinal;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import ibrahim.example.beesinernatinal.pojo.Currency;

/**
 * <h1>App for BEES International</h1>
 * <h2>Android Project of MAD305 Course</h2>
 * <p>The purpose of this application is to introduce the company.
 * The content of the introduction is mainly the tool products exported to
 * some countries. Another function is to quickly convert exchange rates.
 * Because the main currency is also denominated in US dollars, users in various
 * countries need to check the current exchange rate in time (for technical reasons,
 * temporarily use a fixed exchange rate for conversion). Another function is contact
 * information. Users of this application can quickly contact various departments of the company.</p>
 * <p>
 *
 * @author  Wusiman Yibulayin (Ibrahim)
 * @version 1.0
 * @since   2020-11-20
 */
public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    public static FloatingActionButton fab;

    // What to use in Exchange, Product and AboutUs fragments
    public static ArrayList<Currency> exchangeRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setVisibility(Toolbar.INVISIBLE);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        //fab.setVisibility(FloatingActionButton.INVISIBLE);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_category, R.id.nav_exchange,
                R.id.nav_about, R.id.nav_contact, R.id.nav_credits)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        exchangeRates = initExchange();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Intent i = new Intent(
                        MainActivity.this, SettingsActivity.class
                );


                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private ArrayList<Currency> initExchange(){
        String[] currency_name = getResources().getStringArray(R.array.currency_name);
        String[] currency_country = getResources().getStringArray(R.array.currency_country);
        String[] currency_rate = getResources().getStringArray(R.array.currency_rate);
        String[] currency_sign = getResources().getStringArray(R.array.currency_sign);
        TypedArray currency_flag = getResources().obtainTypedArray((R.array.currency_flag));

        ArrayList<Currency> currencies = new ArrayList<>();

        for (int i = 1; i < currency_country.length; i++){
            currencies.add(new Currency(
                            currency_name[i],
                            currency_country[i],
                            currency_sign[i],
                            Double.parseDouble(currency_rate[i]),
                            currency_flag.getResourceId(i,-1)
                    )
            );
        }

        return currencies;
    }
}