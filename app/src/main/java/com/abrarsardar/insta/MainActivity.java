package com.abrarsardar.insta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.abrarsardar.appc.R;
import com.abrarsardar.appc.databinding.ActivityMainBinding;
import com.abrarsardar.insta.ui.dashboard.DashboardFragment;
import com.abrarsardar.insta.ui.heart.HeartFragment;
import com.abrarsardar.insta.ui.home.HomeFragment;
import com.abrarsardar.insta.ui.notifications.NotificationsFragment;
import com.abrarsardar.insta.ui.user.UserFragment;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import org.jetbrains.annotations.NotNull;



public class                                                                     MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageView opencamera;
    private ImageView imageview;

    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContainer, new HomeFragment());
        transaction.commit();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        opencamera = findViewById(R.id.openCamera);
        imageview = findViewById(R.id.imageView);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
        {
            @Override
            public void onActivityResult(ActivityResult result) {
              Bundle bundle =  result.getData().getExtras();
                Bitmap photo = (Bitmap) bundle.get("data");
                imageview.setImageBitmap(photo);

            }

        });

        opencamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opencamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(opencamera);
            }
        });

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        transaction.replace(R.id.flContainer, new HomeFragment());
                        break;
                    case R.id.navigation_dashboard:
                        transaction.replace(R.id.flContainer, new DashboardFragment());
                        break;
                    case R.id.navigation_notifications:
                        transaction.replace(R.id.flContainer, new NotificationsFragment());
                        ImagePicker.with(MainActivity.this)
                           .crop()	    			//Crop image(Optional), Check Customization for more option
                           .compress(1024)			//Final image size will be less than 1 MB(Optional)
                           .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                         .start();
                        break;
                    case R.id.navigation_heart:
                        transaction.replace(R.id.flContainer, new HeartFragment());
                        break;
                    case R.id.navigation_user:
//                        transaction.replace(R.id.flContainer, new UserFragment());
                        Intent intent = new Intent(MainActivity.this, User.class);
                        startActivity(intent);
                        break;
                }
                transaction.commit();
                return true;
            }
        });
    }
}










