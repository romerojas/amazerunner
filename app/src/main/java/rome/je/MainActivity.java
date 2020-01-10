package rome.je;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.github.capur16.digitspeedviewlib.DigitSpeedView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    AppCompatSeekBar seek;
    DigitSpeedView digitSpeedView;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    bottomNav = (BottomNavigationView) findViewById(R.id.bottomnav);
    Menu menu = bottomNav.getMenu();
    MenuItem menuItem = menu.getItem(0);
    menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:

                        return true;

                    case R.id.run:
                        Intent intent2 = new Intent(MainActivity.this, MapBox.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        return true;

                    case R.id.profile:
                        Intent intent3 = new Intent(MainActivity.this, Prof.class);
                        startActivity(intent3);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        return true;

                    default:
                    return false;
                }


            }
        });

        start = (Button) findViewById(R.id.startBtn);
        seek = (AppCompatSeekBar) findViewById(R.id.seek);
        digitSpeedView = (DigitSpeedView) findViewById(R.id.digiV);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapBox.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                digitSpeedView.updateSpeed(progress+5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
