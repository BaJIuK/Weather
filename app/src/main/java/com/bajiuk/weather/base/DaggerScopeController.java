package com.bajiuk.weather.base;

import android.os.Bundle;
import com.bluelinelabs.conductor.Controller;
import java.util.HashMap;
import java.util.Map;

public abstract class DaggerScopeController<T> extends Controller {
  private static final Map<String, Object> componentsMap = new HashMap<>();

  protected DaggerScopeController() {
  }

  protected DaggerScopeController(Bundle args) {
    super(args);
  }

  protected abstract T buildComponent();

  protected T getComponent() {
    String name = getComponentName();
    if (!componentsMap.containsKey(name)) {
      componentsMap.put(name, buildComponent());
    }
    return (T) componentsMap.get(name);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    componentsMap.remove(getComponentName());
  }

  protected String getComponentName() {
    return this.getClass().getSimpleName();
  }
}
