#import <Cordova/CDVPlugin.h>


@interface CDVUserAuth : CDVPlugin
{
    NSString *callbackId;
}

@property (nonatomic, retain) NSString *callbackId;

- (void)login:(CDVInvokedUrlCommand*)command;
- (void)logout:(CDVInvokedUrlCommand*)command;

@end
