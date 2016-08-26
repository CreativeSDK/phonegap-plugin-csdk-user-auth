## Members

<dl>
<dt><a href="#CSDKUserAuth">CSDKUserAuth</a></dt>
<dd><p>A global object that lets you log users in and out with their Adobe ID.</p>
</dd>
</dl>

## Typedefs

<dl>
<dt><a href="#loginSuccessCallback">loginSuccessCallback</a> : <code>function</code></dt>
<dd><p>Called if the user login was successful.</p>
</dd>
<dt><a href="#logoutSuccessCallback">logoutSuccessCallback</a> : <code>function</code></dt>
<dd><p>Called if the user logout was successful. No arguments are returned.</p>
</dd>
<dt><a href="#failureCallback">failureCallback</a> : <code>function</code></dt>
<dd><p>Called if the user login or logout fails.</p>
</dd>
<dt><a href="#UserObject">UserObject</a> : <code>Object</code></dt>
<dd><p>A JSON object containing user data.</p>
</dd>
</dl>

<a name="CSDKUserAuth"></a>

## CSDKUserAuth
A global object that lets you log users in and out with their Adobe ID.

**Kind**: global variable  

* [CSDKUserAuth](#CSDKUserAuth)
    * [.login(loginSuccessCallback, failureCallback)](#CSDKUserAuth.login)
    * [.logout(logoutSuccessCallback, failureCallback)](#CSDKUserAuth.logout)

<a name="CSDKUserAuth.login"></a>

### CSDKUserAuth.login(loginSuccessCallback, failureCallback)
Launches the User Auth UI component so a user can log in with their Adobe ID.

**Kind**: static method of <code>[CSDKUserAuth](#CSDKUserAuth)</code>  

| Param | Type | Description |
| --- | --- | --- |
| loginSuccessCallback | <code>[loginSuccessCallback](#loginSuccessCallback)</code> | See type definition. |
| failureCallback | <code>[failureCallback](#failureCallback)</code> | See type definition. |

<a name="CSDKUserAuth.logout"></a>

### CSDKUserAuth.logout(logoutSuccessCallback, failureCallback)
Logs the user out of their Adobe ID.

**Kind**: static method of <code>[CSDKUserAuth](#CSDKUserAuth)</code>  

| Param | Type | Description |
| --- | --- | --- |
| logoutSuccessCallback | <code>[logoutSuccessCallback](#logoutSuccessCallback)</code> | See type definition. |
| failureCallback | <code>[failureCallback](#failureCallback)</code> | See type definition. |

<a name="loginSuccessCallback"></a>

## loginSuccessCallback : <code>function</code>
Called if the user login was successful.

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| userObject | <code>[UserObject](#UserObject)</code> | See type definition. |

<a name="logoutSuccessCallback"></a>

## logoutSuccessCallback : <code>function</code>
Called if the user logout was successful. No arguments are returned.

**Kind**: global typedef  
<a name="failureCallback"></a>

## failureCallback : <code>function</code>
Called if the user login or logout fails.

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| error | <code>string</code> | Error message. |

<a name="UserObject"></a>

## UserObject : <code>Object</code>
A JSON object containing user data.

**Kind**: global typedef  
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| adobeID | <code>string</code> | The Adobe ID of the user. |
| displayName | <code>string</code> | The display name of the user. |
| firstName | <code>string</code> | The first name of the user. |
| lastName | <code>string</code> | The last name of the user. |
| email | <code>string</code> | The email address of the user. |

