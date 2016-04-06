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

#import <AdobeCreativeSDKCore/AdobeCreativeSDKCore.h>
#import "CDVUserAuth.h"
#import "NSObject+PropertiesAsDictionary.h"
#import "NSArray+ItemAsDictionary.h"

#define isEqualIgnoreCaseToString(string1, string2) ([string1 caseInsensitiveCompare:string2] == NSOrderedSame)

@implementation CDVUserAuth

@synthesize callbackId;

- (void) login:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        __weak CDVPlugin* weakSelf = self;

        void(^loginSuccess)(AdobeAuthUserProfile*)= ^(AdobeAuthUserProfile* profile) {
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                          messageAsDictionary:[profile propertiesAsDictionary]];
            [weakSelf.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        };

        void(^loginFailure)(NSString*)= ^(NSString* errorMessage) {
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:errorMessage];
            [weakSelf.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        };

        if([AdobeUXAuthManager sharedManager].authenticated) {
            loginSuccess([AdobeUXAuthManager sharedManager].userProfile);
        } else {
            [[AdobeUXAuthManager sharedManager] login:self.viewController
                onSuccess:^(AdobeAuthUserProfile* profile) {
                    loginSuccess(profile);
                } onError:^(NSError *error) {
                    loginFailure([error localizedDescription]);
            }];
        }
    }];
}

- (void) logout:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        __weak CDVPlugin* weakSelf = self;

        [[AdobeUXAuthManager sharedManager] logout:^{
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [weakSelf.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } onError:^(NSError *error) {
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[error localizedDescription]];
            [weakSelf.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }];
    }];
}

@end
