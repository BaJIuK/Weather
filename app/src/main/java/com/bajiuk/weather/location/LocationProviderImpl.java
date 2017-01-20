package com.bajiuk.weather.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;

/**
 * Created by Valentin on 20.01.2017.
 */

public class LocationProviderImpl implements LocationProvider {

  private ReactiveLocationProvider rxLocation;
  private Context context;

  public LocationProviderImpl(Context context) {
    this.context = context;
    rxLocation = new ReactiveLocationProvider(context);
  }

  @Override public Observable<Location> getCurrentLocation() {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      return Observable.fromCallable(() -> {
        throw new PermissionException();
      });
    } else {
      return rxLocation.getLastKnownLocation();
    }
  }
}
