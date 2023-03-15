export interface CapacitorDeviceSensorsPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getOrientation(): Promise<Orientation>;
  watchOrientation(options: OrientationOptions, callback: OrientationCallback): Promise<string>;
}

// response from native
export interface Orientation {
  heading: string;
}

export type OrientationCallback = (message: Orientation | null, err?: any) => void;

export interface OrientationOptions {
  timeout: number;                   /** milliseconds. Duration of timeout before new measuring */
}