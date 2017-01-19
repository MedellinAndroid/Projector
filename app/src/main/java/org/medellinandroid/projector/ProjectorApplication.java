package org.medellinandroid.projector;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * User: HUGE-gilbert
 * Date: 1/16/17
 * Time: 2:41 PM
 */
public class ProjectorApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
