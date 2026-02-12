import shared
@testable import Drappula

enum SlackRepositoryFactory {
    static func create() -> SlackRepository {
        let networkModule = NetworkModule(factory: HttpEngineFactory(), token: "")
        let converter = FeedbackConverter(channelId: "", platform: "test")
        let cloud = SlackCloud(service: networkModule.service, feedbackConverter: converter)
        return SlackRepository(cloud: cloud, instantProvider: SystemInstantTimeProvider())
    }
}
