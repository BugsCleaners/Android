In this tutorial we implement biometric authentication using android studio. 

Summary of security learned during the implementation: 

1- 3rd party Apps do not not have the ability to distinguish between different fingerprints.

2- If you change your fingerprint records on your device, you will need to activate Touch ID again the next time you log onto the app.

----------------------------------------------------------------------------------------------------------------------------------------------------------
In reference to the following document: Available at https://source.android.com/security/keystore also https://mobile-security.gitbook.io/mobile-security-testing-guide/android-testing-guide/0x05f-testing-local-authentication


An app can request fingerprint authentication by instantiating a fingerprintmanager object and calling its authenticate method. The caller registers callback methods to handle possible outcomes of the authentication process (i.e. success, failure, or error). Incase of an error message the reason of authentication error is also sent. 

