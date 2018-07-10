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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class MainActivity extends AppCompatActivity {

    int value = 0;
    private RadioGroup radioGroup;

    private static final int CAMERA_REQUEST = 1000;
    ImageView imageView;

    public ViewPager viewPager;
    private SectionsPagerAdapter SectionsPagerAdapter;
    public FragmentAdapter adapter;
    
    public void display(int value) {
        TextView scoreView = (TextView) findViewById(R.id.textUpdate);
        scoreView.setText(String.valueOf(value));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.pager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(SectionsPagerAdapter);

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
                                RadioButton rb = (RadioButton)
                                radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

                                EditText editText = (EditText)findViewById(R.id.editText);
                                String textEntered = editText.getText().toString();
                                Intent nextActivity = new Intent(MainActivity.this, SecondActivity.class);
                                nextActivity.putExtra("kuch_bhi","You entered: " + textEntered
                                        + "\n\nCurrent Count: " + value + "\n\nRadioButton Checked: #" + rb.getText());
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

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (rb.isChecked()) {
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Fragment1 fragment1 = new Fragment1();
                    return fragment1;

                case 1:
                    Fragment2 fragment2 = new Fragment2();
                    return fragment2;

                case 2:
                    Fragment3 fragment3 = new Fragment3();
                    return fragment3;

                default:
                    return null;
            }
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
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
                Intent intent = getIntent();
                finish();
                startActivity(intent);

                Toast toast = Toast.makeText(this, "App reset!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 60, 170);
                toast.show();
            }

            case R.id.notify: {
                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle("Hey there!")
                        .setContentText("Tap here to reset the app state.")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher);

                NotificationManager xyz = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                xyz.notify(001, builder.build());
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(0, builder.build());


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
