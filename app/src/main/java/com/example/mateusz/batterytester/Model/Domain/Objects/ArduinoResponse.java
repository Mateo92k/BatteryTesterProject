package com.example.mateusz.batterytester.Model.Domain.Objects;

public class ArduinoResponse {
    private String _temperature;

    private String _voltage;

    private String _current;

    private Double _time;

    private String _capacity;

    private Boolean _stop;

    public String get_temperature() {
        return _temperature;
    }

    public void set_temperature(String _temperature) {
        this._temperature = _temperature;
    }

    public String get_voltage() {
        return _voltage;
    }

    public void set_voltage(String _voltage) {
        this._voltage = _voltage;
    }

    public String get_current() {
        return _current;
    }

    public void set_current(String _current) {
        this._current = _current;
    }

    public Double get_time() {
        return _time;
    }

    public void set_time(Double _time) {
        this._time = _time;
    }

    public Boolean get_stop() {
        return _stop;
    }

    public void set_stop(Boolean _stop) {
        this._stop = _stop;
    }

    public String get_capacity() {
        return _capacity;
    }

    public void set_capacity(String _capacity) {
        this._capacity = _capacity;
    }
}
