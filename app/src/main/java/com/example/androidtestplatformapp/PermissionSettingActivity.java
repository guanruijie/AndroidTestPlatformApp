package com.example.androidtestplatformapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.dream.DreamInterfaceManager;
import android.widget.TextView;

public class PermissionSettingActivity extends AppCompatActivity {
    public static DreamInterfaceManager dreamInterfaceManager =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_setting);
        Button CameraPermissionBtn=findViewById(R.id.CameraPemissionSetTrueBtn);
        if (dreamInterfaceManager==null){
            dreamInterfaceManager=(DreamInterfaceManager)getSystemService("dream_interface_service");
        }
        findViewById(R.id.CameraPemissionSetTrueBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dreamInterfaceManager.setDreamCameraEnabled(true);
            }
        });
        findViewById(R.id.cameraPemissionSetFalseBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dreamInterfaceManager.setDreamCameraEnabled(false);
            }
        });
        findViewById(R.id.CameraPemissionGetBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.CameraPermissionState)).setText(String.valueOf(dreamInterfaceManager.getDreamCameraEnabled()));
            }
        });
        findViewById(R.id.FlyModeSetTrueBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dreamInterfaceManager.setDreamWifiStatus(true);
            }
        });
    }

}