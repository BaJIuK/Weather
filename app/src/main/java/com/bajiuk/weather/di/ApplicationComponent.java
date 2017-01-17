package com.bajiuk.weather.di;

import com.bajiuk.weather.api.di.WeatherApiModule;
import dagger.Component;

@Component(modules = {
    ApplicationModule.class, RetrofitModule.class, WeatherApiModule.class
}) public interface ApplicationComponent {
}
