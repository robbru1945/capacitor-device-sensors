package it.bruco.plugins.capacitor;

import static android.content.Context.SENSOR_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.getcapacitor.JSObject;

import java.util.Timer;
import java.util.TimerTask;

public class CapacitorDeviceSensors implements SensorEventListener {

    // device sensor manager
    private SensorManager mSensorManager;
    protected Orientation orientationData = new Orientation();

    CapacitorDeviceSensors(SensorManager sensorManager) {
        this.mSensorManager = sensorManager;

        // register listeners for the system's orientation sensor
        mSensorManager.registerListener(
                this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

    }

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // get the angle around the z-axis rotated
        float degree = Math.round(sensorEvent.values[0]);
        orientationData.setHeading(Float.toString(degree));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // nothing
    }


    public void requestOrientationUpdates(int timeout, final OrientationResultCallback resultCallback) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {
                resultCallback.success(orientationData);
            }
        }, 0, timeout); // 60 *1000 = 1 min
    }
}
