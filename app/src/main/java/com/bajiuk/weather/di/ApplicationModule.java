package com.bajiuk.weather.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * @author KoiDev
 * @email DevSteelKoi@gmail.com
 */

@Module public class ApplicationModule {
  private Context context;

  public ApplicationModule(Context context) {this.context = context;}

  @Singleton @Provides Context provideContext() {
    return context;
  }
}
