package rome.je;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Prof extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private Button mLogout,achieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);


        bottomNav = (BottomNavigationView) findViewById(R.id.bottomnav);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        Intent intent1 = new Intent(Prof.this, MainActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                        return true;

                    case R.id.run:
                        Intent intent2 = new Intent(Prof.this, MapBox.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                        return true;

                    case R.id.profile:

                        return true;

                    default:
                        return false;
                }


            }
        });
        mLogout = (Button)findViewById(R.id.logout);
        achieve = (Button)findViewById(R.id.achieve);
        achieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Prof.this,Achievements.class);
                startActivity(intent);
            }
        });
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(Prof.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
    }
}
