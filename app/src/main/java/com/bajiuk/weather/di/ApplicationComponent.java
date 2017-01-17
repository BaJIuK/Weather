package com.bajiuk.weather.di;

import dagger.Component;
import javax.inject.Singleton;

@Component(modules = {
    ApplicationModule.class, RetrofitModule.class
}) public interface ApplicationComponent {
}
