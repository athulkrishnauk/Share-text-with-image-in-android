package com.example.user.sharebuddy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ShareForm extends AppCompatActivity implements View.OnClickListener {

    ImageView img;
    Button btn_share;
    EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_form);

        btn_share = (Button) findViewById(R.id.shareit);
        data = (EditText) findViewById(R.id.editText100);
        img = (ImageView) findViewById(R.id.imageView2);

        btn_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent in = new Intent(Intent.ACTION_PICK);
        in.setType("image/*");
        startActivityForResult(in, 1);
    }

    protected void onActivityResult(int requestcode, int resultcode, Intent imageurlintent) {

        super.onActivityResult(requestcode, resultcode, imageurlintent);
        String datas = data.getText().toString();
        Uri imageuri = null;
        if (resultcode == RESULT_OK) {
            imageuri = imageurlintent.getData();
            Log.e("Image",imageuri.toString());
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            sharingIntent.setType("*/*");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, datas);
            sharingIntent.putExtra(Intent.EXTRA_STREAM, imageuri);
            startActivity(Intent.createChooser(sharingIntent, "Share it"));
        }
    }
}