import Foundation

@objc public class CapacitorDeviceSensors: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
