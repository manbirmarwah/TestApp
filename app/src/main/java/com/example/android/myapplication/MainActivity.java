package com.example.android.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class MainActivity extends AppCompatActivity {

    int value = 0;

    private static final int CAMERA_REQUEST = 1000;
    ImageView imageView;

    private ViewPager viewPager;
    private FragmentAdapter adapter;
    
    public void display(int value) {
        TextView scoreView = (TextView) findViewById(R.id.textUpdate);
        scoreView.setText(String.valueOf(value));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        Button b1 = (Button) findViewById(R.id.button_next);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("This will take you to next intent. Are you sure you wanna make this action?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText editText = (EditText)findViewById(R.id.editText);
                                String textEntered = editText.getText().toString();
                                Intent nextActivity = new Intent(MainActivity.this, SecondActivity.class);
                                nextActivity.putExtra("kuch_bhi","You entered: " + textEntered + "\n\nCurrent Count: " + value);
                                startActivity(nextActivity);
                            }
                        })
                        .setNegativeButton("No",null).show();

            }
        });

        imageView = (ImageView) findViewById(R.id.image);
        Button photoButton = (Button) findViewById(R.id.bCam);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, CAMERA_REQUEST);
            }
        });

        ImageButton B4 = (ImageButton) findViewById(R.id.implicit1);
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent google = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.in/"));
                startActivity(google);
            }
        });

        ImageButton B5 = (ImageButton) findViewById(R.id.implicit2);
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:9999999999"));
                startActivity(dialer);
            }
        });

        ImageButton B6 = (ImageButton) findViewById(R.id.implicit3);
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/"));
                startActivity(fb);
            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("This will take you to next intent. Are you sure you wanna make this action?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "You clicked Yes!",Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = alert.create();

    }

    /**private void setSupportActionBar(Toolbar myToolbar) {
        // Override methods
    }**/

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Starting...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Paused!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {

        super.onResume();
        Toast.makeText(this, "Resuming...", Toast.LENGTH_SHORT).show();
        Intent currentIntent = this.getIntent();
        Bundle extras = currentIntent.getExtras();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Stopping...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
    }

    public void B1(View view) {
        value += 1;
        display(value);
    }

    public void B2(View view) {
        value = 0;
        display(value);

        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setText("");
    }

    public void B3(View view) {
        value -= 1;
        display(value);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

    }

    public void rotate(View view) {
        ImageView img = (ImageView) findViewById(R.id.image);
        Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        img.startAnimation(a1);
    }

    public void zoom(View view) {
        ImageView img = (ImageView) findViewById(R.id.image);
        Animation a2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom);
        img.startAnimation(a2);
    }

    public void blink(View view) {
        ImageView img = (ImageView) findViewById(R.id.image);
        Animation a3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        img.startAnimation(a3);
    }

    public void sendNotification(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder abc = new NotificationCompat.Builder(this)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Hey there!")
                .setContentText("Tap here to reset the app state.")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager xyz = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        abc.setContentIntent(pendingIntent);
        abc.setAutoCancel(true);

        xyz.notify(001, abc.build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add: {
                TextView update = (TextView) findViewById(R.id.actionText);
                update.setText("Add is clicked!");
                return true;
            }

            case R.id.reset: {
                TextView update = (TextView) findViewById(R.id.actionText);
                update.setText("Revert is clicked!");
                return true;
            }

            case R.id.notify: {
                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationCompat.Builder abc = new NotificationCompat.Builder(this)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle("Hey there!")
                        .setContentText("Tap here to reset the app state.")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher);

                NotificationManager xyz = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                abc.setContentIntent(pendingIntent);
                abc.setAutoCancel(true);
                xyz.notify(001, abc.build());

                return true;
            }

            case R.id.about: {
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                new AlertDialog.Builder(this)
                        .setMessage("This is a basic app to demonstrate various feaures of Android.")
                        .setCancelable(false)
                        .setNegativeButton("Dismiss",null).show();
                AlertDialog alertDialog = adb.create();
                alertDialog.show();
                return true;
            }

            case R.id.action_exit: {
                finish();
            }
        }

        return (super.onOptionsItemSelected(item));
    }

}
