package fr.smb.bigbrother.annexe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import fr.smb.bigbrother.MainActivity;
import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Util;

public class Authentification extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    SignInButton signInButton;
    Button bValider;
    TextView tvMail, tvError;
    EditText edCard;

    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.auth);

        Util.setTitle(this,"Authentification");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        tvMail = findViewById(R.id.tvMailAuth);
        tvError = findViewById(R.id.tvErrorAuth);
        edCard = findViewById(R.id.edAuth);

        signInButton = findViewById(R.id.signIn);
        signInButton.setOnClickListener(view -> {
            try {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(status -> {
                    Util.print("sign out");
                });
            }catch(IllegalStateException ignored){
                ignored.printStackTrace();
            }
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        bValider = findViewById(R.id.bAuthValider);
        bValider.setOnClickListener(view -> {
            if(acct == null){
                tvError.setText("Veuillez vous connecter à une adresse mail");
            }else if(!acct.getEmail().split("@")[1].equals("stemariebeaucamps.fr")){
                tvError.setText("Veuillez vous connecter à une adresse mail beaucamps");
            }else if(edCard.getText().toString().length() == 0) {
                tvError.setText("Veuillez renseigner votre numéro de carte");

            }else {
                try {
                    int tag = Integer.parseInt(edCard.getText().toString());
                    if (edCard.getText().toString().length() != 5) {
                        tvError.setText("Veuillez renseigner un numéro de carte à 5 chiffres");
                    } else {
                        firebaseAuthWithGoogle(acct.getIdToken(),tag);
                    }
                } catch (Exception ex) {
                    tvError.setText("Veuillez renseigner un numéro de carte valide");
                }
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        Log.w(TAG,"handleSignInResult: " + result.isSuccess());
        if(result.isSuccess()){
            acct = result.getSignInAccount();


            tvMail.setText(acct.getEmail());
            Util.print("email" , acct.getEmail());


        }else{
            Util.print("email" , "error");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "connection failed : " + connectionResult);
    }


    //Auth.GoogleSignInApi.signOut(mGoogleApiClient)


    private void firebaseAuthWithGoogle(String idToken, int tag) {
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        SharedPreferences preferences = getSharedPreferences("prefData", Context.MODE_WORLD_WRITEABLE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("name",acct.getDisplayName());
                        editor.putInt("tag",tag);
                        editor.putBoolean("logged" ,true);
                        editor.apply();
                        Intent i = new Intent(Authentification.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }
                });
    }

}
