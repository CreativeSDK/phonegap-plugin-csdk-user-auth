/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/

package com.adobe.phonegap.csdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.adobe.creativesdk.foundation.auth.AdobeUXAuthManager;
import com.adobe.creativesdk.foundation.auth.AdobeAuthSessionHelper;
import com.adobe.creativesdk.foundation.auth.AdobeAuthSessionLauncher;
import com.adobe.creativesdk.foundation.auth.AdobeAuthException;

public class AdobeLoginActivity extends Activity {
    private static final String LOG_TAG = "CreativeSDK_UserAuth";

    private AdobeUXAuthManager mUXAuthManager = AdobeUXAuthManager.getSharedAuthManager();
    private AdobeAuthSessionHelper mAuthSessionHelper;

    private AdobeAuthSessionHelper.IAdobeAuthStatusCallback mStatusCallback = new AdobeAuthSessionHelper.IAdobeAuthStatusCallback() {
        @Override
        public void call(AdobeAuthSessionHelper.AdobeAuthStatus adobeAuthStatus, AdobeAuthException e) {
            if (AdobeAuthSessionHelper.AdobeAuthStatus.AdobeAuthLoggedIn == adobeAuthStatus) {
                Log.d(LOG_TAG, "user is authenticated");
                returnLoggedIn();
            } else {
                Log.d(LOG_TAG, "user is un-authenticated");
                showAdobeLoginUI();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate of AdobeLoginActivity");
        mAuthSessionHelper = new AdobeAuthSessionHelper(mStatusCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuthSessionHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuthSessionHelper.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuthSessionHelper.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuthSessionHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuthSessionHelper.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAuthSessionHelper.onActivityResult(requestCode, resultCode, data);
    }

    private void showAdobeLoginUI() {
        AdobeAuthRedirectCredentialsApp mApplication = (AdobeAuthRedirectCredentialsApp)getApplicationContext();
        final String[] authScope = {"email", "profile", "address"};
        mUXAuthManager.login(new AdobeAuthSessionLauncher.Builder()
                .withActivity(this)
                .withRedirectURI(mApplication.getRedirectUri())
                .withAdditonalScopes(authScope)
                .withRequestCode(200) // Can be any int
                .build()
        );
    }

    private void returnLoggedIn() {
        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }
}
