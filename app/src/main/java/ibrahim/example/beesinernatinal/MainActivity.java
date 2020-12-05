package ibrahim.example.beesinernatinal;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

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
                R.id.nav_about, R.id.nav_contact)
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

        ArrayList<Currency> currencies = new ArrayList<>();

        for (int i = 1; i < currency_country.length; i++){
            currencies.add(new Currency(
                            currency_name[i],
                            currency_country[i],
                            currency_sign[i],
                            Double.parseDouble(currency_rate[i])
                    )
            );
        }

        return currencies;
    }
}