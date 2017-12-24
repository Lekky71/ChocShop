package com.hashcode.chocshop.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.hashcode.chocshop.BuildConfig
import com.hashcode.chocshop.R
import java.util.Arrays


class SignInActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 123
    val auth = FirebaseAuth.getInstance()!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        if(auth.currentUser != null){

        }
        else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                            .setAvailableProviders(
                                    Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                            .setTosUrl("link to app terms and service")
                            .setPrivacyPolicyUrl("link to app privacy policy")
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val response = IdpResponse.fromResultIntent(data)
            if(resultCode == Activity.RESULT_OK){
//                startActivity(Next Activity)
                showSnackbar(R.string.signed_in)
                finish()
                return
            }
            else {
                if(response == null){
                    showSnackbar(R.string.sign_in_cancelled)
                    return
                }
                if(response.errorCode == ErrorCodes.NO_NETWORK){
                    showSnackbar(R.string.no_internet_connection)
                    return
                }
                if(response.errorCode == ErrorCodes.UNKNOWN_ERROR){
                    showSnackbar(R.string.unknown_error)
                    return
                }
            }
        }
        showSnackbar(R.string.unknown_sign_in_response)
    }
    fun showSnackbar(id : Int){
        Snackbar.make(findViewById(R.id.sign_in_container), resources.getString(id), Snackbar.LENGTH_LONG)
    }
}
