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

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import com.adobe.creativesdk.foundation.auth.AdobeUXAuthManager;
import com.adobe.creativesdk.foundation.auth.AdobeAuthUserProfile;

/**
* This class exposes methods in Cordova that can be called from JavaScript.
*/
public class UserAuth extends CordovaPlugin {
    private static final String LOG_TAG = "CreativeSDK_UserAuth";

    private AdobeUXAuthManager mUXAuthManager = AdobeUXAuthManager.getSharedAuthManager();

    public CallbackContext callbackContext;

     /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback context from which we were invoked.
     */
    @SuppressLint("NewApi")
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;

        final CordovaPlugin that = this;
        if (action.equals("login")) {
            Log.d(LOG_TAG, "login");
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    if (mUXAuthManager.isAuthenticated()) {
                        callbackContext.success(createProfileObject());
                    } else {
                        Intent intent = new Intent(cordova.getActivity(), AdobeLoginActivity.class);
                        cordova.startActivityForResult((CordovaPlugin) that, intent, 2);
                    }
                }
            });
        } else if (action.equals("logout")) {
            Log.d(LOG_TAG, "logout");
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    mUXAuthManager.logout();
                    callbackContext.success();
                }
            });
        } else {
            return false;
        }
        return true;
    }

    /**
    * Called when the camera view exits.
    *
    * @param requestCode       The request code originally supplied to startActivityForResult(),
    *                          allowing you to identify who this result came from.
    * @param resultCode        The integer result code returned by the child activity through its setResult().
    * @param intent            An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
    */
   public void onActivityResult(int requestCode, int resultCode, Intent intent) {
       if (resultCode == Activity.RESULT_OK) {
           switch (requestCode) {
               case 2:
                   this.callbackContext.success(createProfileObject());
                   break;
           }
       } else if (resultCode == Activity.RESULT_CANCELED) {
           this.callbackContext.error("Login Canceled");
       }
   }

   private JSONObject createProfileObject() {
       AdobeAuthUserProfile profile = mUXAuthManager.getUserProfile();

       JSONObject obj = null;
       try {
           obj = new JSONObject();
           obj.put("adobeID", profile.getAdobeID());
           obj.put("description", profile.getDescription());
           obj.put("fullName", profile.getDisplayName());
           obj.put("email", profile.getEmail());
           obj.put("firstName", profile.getFirstName());
           obj.put("lastName", profile.getLastName());
           obj.put("emailVerified", profile.isEmailVerified());
           obj.put("isEnterpriseUser", profile.isEnterpriseUser());
       } catch (JSONException e) {
           // well this should never happen
       }

       return obj;
   }
}
