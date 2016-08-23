/* globals require */

/*!
 * Module dependencies.
 */

var cordova = require('./helper/cordova'),
    CSDKUserAuth = require('../www/UserAuth'),
    execSpy,
    execWin,
    options;

/*!
 * Specification.
 */

describe('phonegap-plugin-csdk-user-auth', function () {
    beforeEach(function () {
        execWin = jasmine.createSpy();
        execSpy = spyOn(cordova.required, 'cordova/exec').andCallFake(execWin);
    });

    describe('User Auth', function () {
        it('should exist', function () {
            expect(CSDKUserAuth).toBeDefined();
            expect(typeof CSDKUserAuth === 'object').toBe(true);
        });

        it('should contain a login function', function () {
            expect(CSDKUserAuth.login).toBeDefined();
            expect(typeof CSDKUserAuth.login === 'function').toBe(true);
        });

        it('should contain a logout function', function () {
            expect(CSDKUserAuth.logout).toBeDefined();
            expect(typeof CSDKUserAuth.logout === 'function').toBe(true);
        });
    });

    describe('CSDKUserAuth instance', function () {
        describe('cordova.exec', function () {
            it('should call cordova.exec on next process tick', function (done) {
                CSDKUserAuth.login(function(profile) {
                }, function() {});
                setTimeout(function () {
                    expect(execSpy).toHaveBeenCalledWith(
                        jasmine.any(Function),
                        jasmine.any(Function),
                        'CSDKUserAuth',
                        'login',
                        jasmine.any(Object)
                    );
                    done();
                }, 100);
            });

            it('should call cordova.exec on next process tick', function (done) {
                CSDKUserAuth.logout(function(profile) {
                }, function() {});
                setTimeout(function () {
                    expect(execSpy).toHaveBeenCalledWith(
                        jasmine.any(Function),
                        jasmine.any(Function),
                        'CSDKUserAuth',
                        'logout',
                        jasmine.any(Object)
                    );
                    done();
                }, 100);
            });
        });
    });
});
