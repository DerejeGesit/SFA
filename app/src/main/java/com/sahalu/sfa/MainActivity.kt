package com.sahalu.sfa

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//            .withListener(object : PermissionListener {
//                override fun onPermissionGranted(response: PermissionGrantedResponse) { /* ... */
//                }
//
//                override fun onPermissionDenied(response: PermissionDeniedResponse) { /* ... */
//                }
//
//                override fun onPermissionRationaleShouldBeShown(
//                    permission: PermissionRequest?,
//                    token: PermissionToken?
//                ) { /* ... */
//                }
           // }).check()
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
