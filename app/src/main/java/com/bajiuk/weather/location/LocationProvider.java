package com.bajiuk.weather.location;

import android.location.Location;
import rx.Observable;

public interface LocationProvider {
  Observable<Location> getCurrentLocation();
}
