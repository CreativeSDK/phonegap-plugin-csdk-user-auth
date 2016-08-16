/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

/* global cordova:false */
/* globals window */

var exec = cordova.require('cordova/exec');

/**
    @description A global object that lets you log users in and out with their Adobe ID.
    @global
*/
var CSDKUserAuth = {
    /**
     * @description Launches the User Auth UI component so a user can log in with their Adobe ID.
     * @function login
     * @memberof UserAuth
     * @param {!loginSuccessCallback} loginSuccessCallback - See type definition.
     * @param {!failureCallback} failureCallback - See type definition.
     */
    login: function(successCallback, failureCallback) {
        exec(successCallback, failureCallback, 'UserAuth', 'login', []);
    },
    /**
     * @description Logs the user out of their Adobe ID.
     * @function logout
     * @memberof UserAuth
     * @param {!logoutSuccessCallback} logoutSuccessCallback - See type definition.
     * @param {!failureCallback} failureCallback - See type definition.
     */
    logout: function(successCallback, failureCallback) {
        exec(successCallback, failureCallback, 'UserAuth', 'logout', []);
    }
};

/**
 * @description Called if the user login was successful.
 *
 * @callback loginSuccessCallback
 * @param {UserObject} userObject - See type definition.
 */

/**
 * @description Called if the user logout was successful. No arguments are returned.
 *
 * @callback logoutSuccessCallback
 */

/**
 * @description Called if the user login or logout fails.
 *
 * @callback failureCallback
 * @param {string} error - Error message.
 */

/**
 * @typedef {Object} UserObject - A JSON object containing user data.
 * @property {string} adobeID - The Adobe ID of the user.
 * @property {string} displayName - The display name of the user.
 * @property {string} firstName - The email address of the user.
 * @property {string} lastName - The first name of the user.
 * @property {string} email - The email address of the user.
 */

module.exports = CSDKUserAuth;
