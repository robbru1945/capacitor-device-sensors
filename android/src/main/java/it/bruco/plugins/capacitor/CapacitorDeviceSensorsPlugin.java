package it.bruco.plugins.capacitor;

import android.content.Context;
import android.hardware.SensorManager;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@CapacitorPlugin(name = "CapacitorDeviceSensors")
public class CapacitorDeviceSensorsPlugin extends Plugin {
    private Map<String, PluginCall> watchingCalls = new HashMap<>();

    private CapacitorDeviceSensors implementation;

    @Override
    public void load() {
        super.load();
        // initialize android device sensor capabilities
        SensorManager mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        implementation = new CapacitorDeviceSensors(mSensorManager);
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void getOrientation(PluginCall call) {

        JSObject ret = new JSObject();
        ret.put("heading", implementation.orientationData.getHeading());
        call.resolve(ret);
    }


    @PluginMethod(returnType = PluginMethod.RETURN_CALLBACK)
    public void watchOrientation(PluginCall call) {
        call.setKeepAlive(true);
        int timeout = call.getInt("timeout", 5000);
        System.out.println("TIMEOUT SET TO " + String.valueOf(timeout));
        OrientationResultCallback callback = new OrientationResultCallback() {
            @Override
            public void success(Orientation orientation) {
                call.resolve(getJSObjectForLocation(orientation));
            }

            @Override
            public void error(String message) {
                call.reject(message);
            }
        };

        implementation.requestOrientationUpdates(timeout, callback);
        watchingCalls.put(call.getCallbackId(), call);
    }


    private JSObject getJSObjectForLocation(Orientation orientation) {
        JSObject obj = new JSObject();
        obj.put("heading", orientation.getHeading());
        return obj;
    }
}
