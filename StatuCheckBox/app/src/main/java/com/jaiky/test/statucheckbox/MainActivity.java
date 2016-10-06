package com.jaiky.test.statucheckbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ImageCheckBox cbSelect1, cbSelect2, cbSelect3;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbSelect1 = (ImageCheckBox) findViewById(R.id.cbSelect1);
        cbSelect2 = (ImageCheckBox) findViewById(R.id.cbSelect2);
        cbSelect3 = (ImageCheckBox) findViewById(R.id.cbSelect3);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbSelect3.setCheckDisabled();
            }
        });

    }




}
