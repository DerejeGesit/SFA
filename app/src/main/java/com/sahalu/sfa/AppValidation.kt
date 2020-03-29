package com.sahalu.sfa
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.regex.Pattern
class AppValidation {
    fun EmptyTextValidation(txtcont: TextView, con: Context, msg: String): Boolean {
        val input = txtcont.text.toString().trim()
        if (input.isEmpty()) {
            //Toast.makeText(con, "Outlet is Empty!", Toast.LENGTH_SHORT).show()
            Toast.makeText(con, msg, Toast.LENGTH_SHORT).show()
            return true
        } else
            return false
    }
    fun EmptyComboValidation(cmdcont: Spinner, con: Context, msg: String): Boolean {
        val input = cmdcont.selectedItem.toString().trim()
        if (input.isEmpty()) {
            //Toast.makeText(con, "Outlet is Empty!", Toast.LENGTH_SHORT).show()
            Toast.makeText(con, msg, Toast.LENGTH_SHORT).show()
            return true
        } else
            return false
    }
    fun PhoneNoValidation(txtcont: TextView, con: Context, msg: String): Boolean {
        try {


            val input = txtcont.text.toString().trim()
            val regexStr = "(^09)[\\d]{8}".toRegex() //"^\\d+09[0-9]{10}"
            val pattern: Pattern = Pattern.compile(regexStr.toString())
            if (!input.isEmpty())
            {
                if (pattern.matcher(input).matches()) {

                    return true
                }
                else
                {
                    Toast.makeText(con, msg, Toast.LENGTH_SHORT).show()
                    return false
                }
            }
            else
            {
                return false;
            }
        }
        catch (e: NumberFormatException)
        {
            return false;
        }

    }
}

class AppPermission {

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
        private lateinit var context: Context
        private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        fun checkLocationPermission(con: Context): Boolean {
            context=con
            return (ContextCompat.checkSelfPermission(context, locationPermission) == PackageManager.PERMISSION_GRANTED)
        }
    }




}