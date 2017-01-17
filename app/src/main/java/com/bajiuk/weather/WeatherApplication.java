/*
 * Copyright 2014 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bajiuk.weather;

import android.app.Application;
import com.bajiuk.weather.di.ApplicationComponent;
import com.bajiuk.weather.di.ApplicationModule;
import com.bajiuk.weather.di.DaggerApplicationComponent;

public class WeatherApplication extends Application {
  private static WeatherApplication instance;
  private ApplicationComponent component;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
  }

  public static ApplicationComponent getAppComponent() {
    if (instance.component == null) {
      instance.component = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(instance))
          .build();
    }
    return instance.component;
  }

}
