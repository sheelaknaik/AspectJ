package com.examp.lib.seclibng.auto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.UUID;

/**
 * This class is responsible for logging the life cycle events.
 */

public class AutoLifecycleLogger implements Application.ActivityLifecycleCallbacks {
    private final String tagLifecycleLogger = "AutoLifecycleLogger.";
    private static String sSessionId;
    private final boolean mLogCreatedDestroyed = true;
    private final boolean mLogStartedStopped = true;
    private final boolean mLogResumedPaused = true;
    private static Application mApp;
    private int mSessionDepth;
    private long onCreateTime = 0;
    private static String currentActivity;

    /***
     * enum for activity Lifecycle events
     */
    public enum ActivityLifecycle {
        CREATED,
        STARTED,
        RESUMED,
        PAUSED,
        STOPPED,
        DESTROYED
    }

    /***
     * Constructor to initialize the Auto lifecycle related event.
     *
     * @param app application object
     */

    public AutoLifecycleLogger(Application app) {
        mApp = app;
        mSessionDepth = 0;
    }

    /***
     * This method is used to get the session ID
     *
     * @return returns the session ID
     */
    public static String getsSessionId() {
        return sSessionId;
    }

    /***
     * Method use to set the session ID
     *
     * @param sSessionId session id to set
     */
    private static void setsSessionId(String sSessionId) {
        AutoLifecycleLogger.sSessionId = sSessionId;
        if (AutoLifecycleLogger.sSessionId != null) {
            Context context = (Context) mApp;
        }
    }

    /***
     * Register for the activity lifecycle call back events
     */

    public void registerActivityLifecycleCallbacks() {
        if (mApp == null) {
            return;
        }

        mApp.registerActivityLifecycleCallbacks(this);
    }

    /***
     * Stop listening for activity life cycle events
     */
    public void unregisterActivityLifecycleCallbacks() {
        if (mApp == null) {
            return;
        }
        mApp.unregisterActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (!mLogCreatedDestroyed) {
            return;
        }
        try {
            setCurrentActivity(activity.getLocalClassName().replace(".java", ""));
            mSessionDepth++;
            if (mSessionDepth == 1 && sSessionId == null) {
                setsSessionId(UUID.randomUUID().toString());
            }
            onCreateTime = System.currentTimeMillis();
            System.out.println("Activity created>>> ");

        } catch (Exception e) {
            System.out.println("Error while creating event for onActivityCreated");
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onActivityStarted(Activity activity) {

        if (!mLogStartedStopped) {
            return;
        }
        try {
            System.out.println("Activity started>>> ");
        } catch (Exception e) {
            System.out.println("Error while creating event for OnActivityStarted");
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        setCurrentActivity(activity.getLocalClassName());
        if (!mLogResumedPaused) {
            return;
        }
        if (onCreateTime > 0) {
            long currentTime = System.currentTimeMillis();
            long timeDifference = currentTime - onCreateTime;

            onCreateTime = 0;
            try {
                System.out.println("Page load time >>>> ");
            } catch (Exception je) {
                System.out.println("Error while creating event for pageLoadTime");
            }
        }
        try {
            System.out.println("Activity resumed>>>> ");
        } catch (Exception e) {
            System.out.println("Error while creating event for OnActivityResumed");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (!mLogResumedPaused) {
            return;
        }
        try {
            System.out.println("Activity paused>>>> ");
        } catch (Exception e) {
            System.out.println("Error while creating event for onActivityPaused");
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (!mLogStartedStopped) {
            return;
        }
        try {
            System.out.println("activity stopped>>>");

        } catch (Exception e) {
            System.out.println("Error while creating event for OnActivityStopped");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        System.out.println("OnActivitySaveInstanceState  ");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (!mLogCreatedDestroyed) {
            return;
        }
        if (mSessionDepth > 0) {
            mSessionDepth--;
        }
        try {
            System.out.println("activity destroyed>>> ");

        } catch (Exception e) {
            System.out.println("Error while creating event for onActivityDestroyed");
        }
    }







    /***
     * Sets current activity name
     *
     * @param currentActivity Current activity name
     */
    private static void setCurrentActivity(String currentActivity) {
        AutoLifecycleLogger.currentActivity = currentActivity;
    }


}
