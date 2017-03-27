package com.daltonicchameleon.portfolio.util.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * portfolio-app
 * Created in 3/27/17 by the following authors:
 * Pedro Okawa
 */
public class AuthenticationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new AccountAuthenticator(this).getIBinder();
    }
}
