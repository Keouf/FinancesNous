package com.example.pcportablevidjay.financesnous;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownHostException;

import classes.MyDBHelper;
import classes.StorageHelper;
import classes.Utilisateur;
import classes.Utils;


public class Login_Activity extends AppCompatActivity {

    private final MyDBHelper myDBHelper = new MyDBHelper();
    private UserLoginTask mAuthTask = null;
    private Utilisateur user;
    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private TextView mProgressTextView;


    /////////////////////////
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Login_Activity.this.receivedBroadcast(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter iff = new IntentFilter();
        iff.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        // Put whatever message you want to receive as the action
        this.registerReceiver(this.mBroadcastReceiver, iff);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.unregisterReceiver(this.mBroadcastReceiver);
    }

    private void receivedBroadcast(Intent i) {
        UpdateIHMInternet();
    }
    /////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        if (Utils.getConnectivityStatus(getApplicationContext())) {
            // check if logged in before.
            if (StorageHelper.fileExists(this.getBaseContext())) {
                Log.e("peter", " fileExists!!!!");
                Intent accueil = new Intent(getBaseContext(), LoadingScreenActivity.class);
                startActivity(accueil);
                finish();
            }
        }

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        TextView link_login_activity = (TextView) findViewById(R.id.link_new_account);
        link_login_activity.setClickable(true);
        link_login_activity.setMovementMethod(LinkMovementMethod.getInstance());
        link_login_activity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent creerCompte = new Intent(getBaseContext(), CreerCompte_Activity.class);
                startActivity(creerCompte);
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Utils.getConnectivityStatus(getApplicationContext())) {
                    UpdateIHMInternet();
                    Toast toast = Toast.makeText(getApplicationContext(), "Activer internet", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    attemptLogin();
                }
            }
        });

        Button mCreerCompteBouton = (Button) findViewById(R.id.btn_creer_compte);
        mCreerCompteBouton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creerCompte = new Intent(getBaseContext(), CreerCompte_Activity.class);
                startActivity(creerCompte);
            }
        });

        mProgressView = findViewById(R.id.login_progress);
        mProgressTextView = (TextView) findViewById(R.id.text_connexion);

        if (!Utils.getConnectivityStatus(getApplicationContext())) {
            findViewById(R.id.textHorsLigne).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonConnexionHorsLigne).setVisibility(View.VISIBLE);
            findViewById(R.id.email_sign_in_button).setEnabled(false);
        }

    }

    private void UpdateIHMInternet() {
        if (!Utils.getConnectivityStatus(getApplicationContext())) {
            findViewById(R.id.textHorsLigne).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonConnexionHorsLigne).setVisibility(View.VISIBLE);
            findViewById(R.id.email_sign_in_button).setEnabled(false);
        } else {
            findViewById(R.id.textHorsLigne).setVisibility(View.GONE);
            findViewById(R.id.buttonConnexionHorsLigne).setVisibility(View.GONE);
            findViewById(R.id.email_sign_in_button).setEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password, this);
            mAuthTask.execute();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 2;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //code exécuté par l'UI thread
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                    int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                    mProgressTextView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
                    mProgressView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
                    mProgressView.animate().setDuration(shortAnimTime).alpha(
                            show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mProgressView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
                        }
                    });
                } else {
                    // The ViewPropertyAnimator APIs are not available, so simply show
                    // and hide the relevant UI components.
                    mProgressView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
                    mProgressTextView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
                }
            }
        });

    }

    public static class NetworkChange extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            // Post the UI updating code to our Handler
            //updateUI(intent);
            Log.e("test", "test connexion");
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final Activity act;

        UserLoginTask(String email, String password, Activity act) {
            this.mEmail = email;
            this.mPassword = password;
            this.act = act;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                user = myDBHelper.getUtilisateur(mEmail);
                StorageHelper.storeObject(act.getBaseContext(), user);
            } catch (UnknownHostException e) {
                return false;
            } catch (Exception e) {
                return false;
            }

            return user.getMotDePasse().equals(mPassword);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                user.setMesDepenses(myDBHelper.getMesDepenses(act));
                StorageHelper.storeObject(act.getBaseContext(), user);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent accueil = new Intent(getBaseContext(), LoadingScreenActivity.class);
                        startActivity(accueil);
                    }
                }).start();
                finish();
            } else {
                showProgress(false);
                mEmailView.setError(getString(R.string.error_incorrect_combo));
                mEmailView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }

    }
}

