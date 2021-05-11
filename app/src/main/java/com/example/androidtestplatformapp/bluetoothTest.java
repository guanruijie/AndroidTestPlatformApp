package com.example.androidtestplatformapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List; //import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import android.widget.ToggleButton;

public class bluetoothTest extends AppCompatActivity {
    static final String targetAddr = "64:69:BC:04:19:C6";


    /**
     * This transfer is confirmed by user.
     */
    public static final int USER_CONFIRMATION_CONFIRMED = 1;
    public static final String USER_CONFIRMATION = "confirm";



    ArrayAdapter<String> adtDevices;
    List<String> lstDevices = new ArrayList<String>();
    BluetoothAdapter btAdapt;
    public static BluetoothSocket btSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_test);
        // 初始化本机蓝牙功能
        btAdapt = BluetoothAdapter.getDefaultAdapter();


        /*
         * 自动接收文件
         */
        BluetoothDevice btDev = btAdapt.getRemoteDevice(targetAddr);

        try {

            Method m = null;
            try {
                m = btDev.getClass().getMethod("createRfcommSocket",
                        new Class[] { int.class });
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                btSocket = (BluetoothSocket) m.invoke(btDev, 1);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            //发送蓝牙配对
            btSocket.connect();

            /*
             * 发送一指定的文件到其它蓝牙设备
             */
            ContentValues cv = new ContentValues();
            cv.put(USER_CONFIRMATION,
                    USER_CONFIRMATION_CONFIRMED);
            getContentResolver().update(
                    Uri.parse("content://com.android.bluetooth.opp/btopp"),
                    cv, null, null);

            btSocket.close();
            Toast.makeText(this,"蓝牙发送", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}