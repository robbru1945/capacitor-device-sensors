import { registerPlugin } from '@capacitor/core';

import type { CapacitorDeviceSensorsPlugin } from './definitions';

const CapacitorDeviceSensors = registerPlugin<CapacitorDeviceSensorsPlugin>('CapacitorDeviceSensors', {
  web: () => import('./web').then(m => new m.CapacitorDeviceSensorsWeb()),
});

export * from './definitions';
export { CapacitorDeviceSensors };
