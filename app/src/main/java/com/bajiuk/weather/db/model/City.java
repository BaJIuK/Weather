package com.bajiuk.weather.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Items") public class City extends Model {
  @Column(name = "Timestamp") public Long timestamp;
  @Column(name = "Name") public String name;
}
