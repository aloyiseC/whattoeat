package com.internetbrands.whattoeat.util

/**
Created by Wei Lai 2020/5/13
Description:
 */
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import java.io.Serializable

/**
Created by Aloyise 2019/1/18
Description:
 */
inline fun <reified T: Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T: Activity> Activity.newIntentForResult(reqeustCode:Int) {
    val intent = Intent(this, T::class.java)
    startActivityForResult(intent,reqeustCode)
}

inline fun <reified T:Activity>  Activity.newIntent(serializable: Serializable){
    val intent = Intent(this,T::class.java)
    intent.putExtra(Constants.DATA_KEY,serializable)
    startActivity(intent)
}

inline fun <reified T:Activity>  Activity.newIntent(value: String){
    val intent = Intent(this,T::class.java)
    intent.putExtra(Constants.DATA_KEY,value)
    startActivity(intent)
}

inline fun <reified T:Activity>  Activity.newIntent(bool:Boolean,serializable: Serializable){
    val intent = Intent(this,T::class.java)
    intent.putExtra(Constants.BOOLEAN_KEY,bool)
    intent.putExtra(Constants.DATA_KEY,serializable)
    startActivity(intent)
}

inline fun <reified T:Activity>  Activity.newIntent(index:Int,serializable: Serializable){
    val intent = Intent(this,T::class.java)
    intent.putExtra(Constants.INDEX_KEY,index)
    intent.putExtra(Constants.DATA_KEY,serializable)
    startActivity(intent)
}

inline fun <reified T:Activity>  Activity.newIntent(index:Int){
    val intent = Intent(this,T::class.java)
    intent.putExtra(Constants.INDEX_KEY,index)
    startActivity(intent)
}


inline fun <reified T:Activity> Activity.newIntentForResult(serializable: Serializable,reqeustCode: Int){
    val intent = Intent(this,T::class.java)
    intent.putExtra(Constants.DATA_KEY,serializable)
    startActivityForResult(intent,reqeustCode)
}

inline fun <reified T: Activity> androidx.fragment.app.Fragment.newIntent() {
    val intent = Intent(activity, T::class.java)
    startActivity(intent)
}

inline fun <reified T: Activity> androidx.fragment.app.Fragment.newIntentForResult(reqeustCode:Int) {
    val intent = Intent(activity, T::class.java)
    startActivityForResult(intent,reqeustCode)
}

inline fun <reified T: Activity> androidx.fragment.app.Fragment.newIntentForResult(reqeustCode:Int,value:Int) {
    val intent = Intent(activity, T::class.java)
    intent.putExtra(Constants.INDEX_KEY,value)
    startActivityForResult(intent,reqeustCode)
}


inline fun <reified T:Activity> androidx.fragment.app.Fragment.newIntent(serializable: Serializable){
    val intent = Intent(activity,T::class.java)
    intent.putExtra(Constants.DATA_KEY,serializable)
    startActivity(intent)
}

inline fun Activity.showToast(note:String){
    Toast.makeText(this,note,Toast.LENGTH_LONG).show()
}

inline fun androidx.fragment.app.Fragment.showToast(note:String){
    Toast.makeText(context,note,Toast.LENGTH_LONG).show()
}