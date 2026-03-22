import shared
@testable import Drappula

enum SlackRepositoryFactory {
    static func create() -> SlackRepository {
        let networkModule = NetworkModule(factory: HttpEngineFactory(), token: "")
        let mapper = FeedbackConverter(channelId: "", platform: "test")
        let cloud = SlackCloud(service: networkModule.service, feedbackMapper: mapper)
        return SlackRepository(cloud: cloud, instantProvider: SystemInstantTimeProvider())
    }
}
