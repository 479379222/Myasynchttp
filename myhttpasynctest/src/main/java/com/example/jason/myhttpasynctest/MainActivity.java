package com.example.jason.myhttpasynctest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button btnHttpTest,btnListTest;
    private TextView txtView;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
        btnHttpTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                client.get("https://www.baidu.com", getParames(), new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        txtView.setText(responseString);
                    }
                });

                client.get("http://pic48.nipic.com/file/20140901/12398452_214548228000_2.jpg", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (statusCode == 200) {
                            BitmapFactory factory = new BitmapFactory();
                            Bitmap bitmap = factory.decodeByteArray(responseBody, 0, responseBody.length);
                            imgView.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        });

        btnListTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MylistActivity.class);
                startActivity(intent);
            }
        });
    }

    private RequestParams getParames(){
        RequestParams params = new RequestParams();
        params.put("user", "penngo");
        params.put("psw", "penngo");
        return params;
    }

    private void initView(){
        btnHttpTest=(Button)findViewById(R.id.btnHttpTest);
        btnListTest=(Button)findViewById(R.id.btnLstTest);
        txtView=(TextView)findViewById(R.id.txtView);
        imgView=(ImageView)findViewById(R.id.imgView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
