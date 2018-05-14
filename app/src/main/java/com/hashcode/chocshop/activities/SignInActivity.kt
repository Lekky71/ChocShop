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

    fun showSnackbar(id : Int){
        Snackbar.make(findViewById(R.id.sign_in_container), resources.getString(id), Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        if(auth.currentUser != null){ //If user is signed in
//                startActivity(Next Activity)
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
            /*
                this checks if the activity result we are getting is for the sign in
                as we can have more than activity to be started in our Activity.
             */
            val response = IdpResponse.fromResultIntent(data)
            if(resultCode == Activity.RESULT_OK){
                /*
                    Checks if the User sign in was successful
                 */
//                startActivity(Next Activity)
                showSnackbar(R.string.signed_in)
                val homeIntent = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(homeIntent)
                finish()
                return
            }
            else {
                if(response == null){
                    //If no response from the Server
                    showSnackbar(R.string.sign_in_cancelled)
                    return
                }
                if(response.errorCode == ErrorCodes.NO_NETWORK){
                    //If there was a network problem the user's phone
                    showSnackbar(R.string.no_internet_connection)
                    return
                }
                if(response.errorCode == ErrorCodes.UNKNOWN_ERROR){
                    //If the error cause was unknown
                    showSnackbar(R.string.unknown_error)
                    return
                }
            }
        }
        showSnackbar(R.string.unknown_sign_in_response) //if the sign in response was unknown
    }

}
