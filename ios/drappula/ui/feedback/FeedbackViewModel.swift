import Foundation
import shared

class SystemInstantTimeProvider: InstantTimeProvider {
    func now() -> KotlinInstant {
        let milliseconds = Int64(Date().timeIntervalSince1970 * 1000)
        return KotlinInstant.companion.fromEpochMilliseconds(epochMilliseconds: milliseconds)
    }
}

@MainActor
class FeedbackViewModel: ObservableObject {
    @Published var selectedType: Feedback.Type_ = .enhancement
    @Published var title: String = ""
    @Published var message: String = ""
    @Published var isLoading: Bool = false
    @Published var isSubmitted: Bool = false
    @Published var error: Error?

    private let repository: SlackRepository
    private let reportHandler: ReportHandler

    var isComplete: Bool {
        !title.trimmingCharacters(in: .whitespaces).isEmpty &&
            !message.trimmingCharacters(in: .whitespaces).isEmpty
    }

    init(repository: SlackRepository, reportHandler: ReportHandler) {
        self.repository = repository
        self.reportHandler = reportHandler
    }

    static func create() -> FeedbackViewModel {
        let token = secretValue(forKey: "SLACK_BOT_TOKEN")
        let channelId = secretValue(forKey: "SLACK_CHANNEL_ID")
        let networkModule = NetworkModule(factory: HttpEngineFactory(), token: token)
        let converter = FeedbackConverter(channelId: channelId, platform: "iOS")
        let cloud = SlackCloud(service: networkModule.service, feedbackConverter: converter)
        let repository = SlackRepository(cloud: cloud, instantProvider: SystemInstantTimeProvider())
        let reportHandler = ReportHandlerFactory.create()
        return FeedbackViewModel(repository: repository, reportHandler: reportHandler)
    }

    private static func secretValue(forKey key: String) -> String {
        if let path = Bundle.main.path(forResource: "Secrets", ofType: "plist"),
           let dict = NSDictionary(contentsOfFile: path),
           let value = dict[key] as? String,
           !value.isEmpty {
            return value
        }
        return ProcessInfo.processInfo.environment[key] ?? ""
    }

    func submit() {
        guard isComplete else { return }
        isLoading = true

        let feedback = Feedback(
            type: selectedType,
            title: title,
            message: message,
            created: nil
        )

        repository.uploadFeedback(value: feedback) { [weak self] _, error in
            DispatchQueue.main.async {
                self?.isLoading = false
                if let error = error {
                    self?.error = error
                    self?.reportHandler.reportException(exception: KotlinThrowable(message: error.localizedDescription))
                } else {
                    self?.isSubmitted = true
                }
            }
        }
    }

    func clearError() {
        error = nil
    }
}
