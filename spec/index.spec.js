/* globals require */

/*!
 * Module dependencies.
 */

var cordova = require('./helper/cordova'),
    UserAuth = require('../www/UserAuth'),
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
            expect(UserAuth).toBeDefined();
            expect(typeof UserAuth === 'object').toBe(true);
        });

        it('should contain a login function', function () {
            expect(UserAuth.login).toBeDefined();
            expect(typeof UserAuth.login === 'function').toBe(true);
        });

        it('should contain a logout function', function () {
            expect(UserAuth.logout).toBeDefined();
            expect(typeof UserAuth.logout === 'function').toBe(true);
        });
    });

    describe('UserAuth instance', function () {
        describe('cordova.exec', function () {
            it('should call cordova.exec on next process tick', function (done) {
                UserAuth.login(function(profile) {
                }, function() {});
                setTimeout(function () {
                    expect(execSpy).toHaveBeenCalledWith(
                        jasmine.any(Function),
                        jasmine.any(Function),
                        'UserAuth',
                        'login',
                        jasmine.any(Object)
                    );
                    done();
                }, 100);
            });

            it('should call cordova.exec on next process tick', function (done) {
                UserAuth.logout(function(profile) {
                }, function() {});
                setTimeout(function () {
                    expect(execSpy).toHaveBeenCalledWith(
                        jasmine.any(Function),
                        jasmine.any(Function),
                        'UserAuth',
                        'logout',
                        jasmine.any(Object)
                    );
                    done();
                }, 100);
            });
        });
    });
});
