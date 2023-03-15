import { WebPlugin } from '@capacitor/core';

import type { CapacitorDeviceSensorsPlugin, Orientation, OrientationCallback, OrientationOptions } from './definitions';

export class CapacitorDeviceSensorsWeb extends WebPlugin implements CapacitorDeviceSensorsPlugin {


  getOrientation(): Promise<Orientation> {
    throw new Error('Method not implemented.');
  }
  
  watchOrientation(options: OrientationOptions, callback: OrientationCallback): Promise<string> {
    console.log(options , callback);
    throw new Error('Method not implemented.');
  }


  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

}
