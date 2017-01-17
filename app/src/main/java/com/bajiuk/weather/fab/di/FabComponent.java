package com.bajiuk.weather.fab.di;

import com.bajiuk.weather.base.PerController;
import com.bajiuk.weather.di.ApplicationComponent;
import com.bajiuk.weather.fab.FabController;
import dagger.Component;

@PerController @Component(modules = {
    FabModule.class
}, dependencies = {
    ApplicationComponent.class
})
public interface FabComponent {
  void inject(FabController controller);
}
