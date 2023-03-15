package it.bruco.plugins.capacitor;

public interface OrientationResultCallback {
    void success(Orientation orientation);
    void error(String message);
}
