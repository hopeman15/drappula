import Foundation

enum SecretsProvider {
    static func secretValue(forKey key: String) -> String {
        if let path = Bundle.main.path(forResource: "Secrets", ofType: "plist"),
           let dict = NSDictionary(contentsOfFile: path),
           let value = dict[key] as? String,
           !value.isEmpty {
            return value
        }
        return ProcessInfo.processInfo.environment[key] ?? ""
    }
}
