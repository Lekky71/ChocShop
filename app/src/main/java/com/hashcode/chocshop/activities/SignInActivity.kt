package com.hashcode.chocshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.hashcode.chocshop.BuildConfig
import com.hashcode.chocshop.R
import java.util.*
import java.util.Arrays.asList



class SignInActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var auth = FirebaseAuth.getInstance()
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
}
