package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ini the views
        mWebsiteEditText = findViewById(R.id.editText_url);
        mLocationEditText = findViewById(R.id.editText_location);
        mShareTextEditText = findViewById(R.id.editText_shareText);
    }

    public void openWebsite(View view) {
        // add url text to a string variable
        String url = mWebsiteEditText.getText().toString();

        //parse the string to uri format
        Uri webpage_uri = Uri.parse(url);

        //create intent
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage_uri);

        //find an activity to handle the request and start the activity else log the request error msg.
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents","Cant handle this request!");
        }

    }

    public void openLocation(View view) {
        //get the edit text string
        String location = mLocationEditText.getText().toString();

        //parse the string to uri object with geo search query
        Uri addressUri = Uri.parse("geo:0,0?q=" + location);

        //create implicit intent
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //check if theres an activity that handles the request
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Cant handle this request");
        }
    }

    public void shareText(View view) {
        //get the edit text value to a string variable.
        String shareText = mShareTextEditText.getText().toString();

        //defining the mime type to text
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_with)
                .setText(shareText)
                .startChooser();
    }

    public void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //check if the activity is available
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Request Cannot be Handled!");
        }
    }
}
