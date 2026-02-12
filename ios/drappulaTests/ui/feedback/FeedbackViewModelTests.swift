import Testing
@testable import Drappula
import shared

@MainActor
struct FeedbackViewModelTests {
    @Test
    func initialStateHasDefaultValues() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())

        #expect(viewModel.selectedType == .enhancement)
        #expect(viewModel.title == "")
        #expect(viewModel.message == "")
        #expect(viewModel.isLoading == false)
        #expect(viewModel.isSubmitted == false)
        #expect(viewModel.error == nil)
    }

    @Test
    func isCompleteReturnsFalseWhenTitleIsEmpty() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())
        viewModel.message = "Some message"

        #expect(viewModel.isComplete == false)
    }

    @Test
    func isCompleteReturnsFalseWhenMessageIsEmpty() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())
        viewModel.title = "Some title"

        #expect(viewModel.isComplete == false)
    }

    @Test
    func isCompleteReturnsFalseWhenTitleIsWhitespace() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())
        viewModel.title = "   "
        viewModel.message = "Some message"

        #expect(viewModel.isComplete == false)
    }

    @Test
    func isCompleteReturnsFalseWhenMessageIsWhitespace() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())
        viewModel.title = "Some title"
        viewModel.message = "   "

        #expect(viewModel.isComplete == false)
    }

    @Test
    func isCompleteReturnsTrueWhenTitleAndMessageAreSet() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())
        viewModel.title = "Some title"
        viewModel.message = "Some message"

        #expect(viewModel.isComplete == true)
    }

    @Test
    func clearErrorSetsErrorToNil() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())
        viewModel.error = NSError(domain: "test", code: 1)

        viewModel.clearError()

        #expect(viewModel.error == nil)
    }

    @Test
    func selectedTypeCanBeChanged() async {
        let viewModel = FeedbackViewModel(repository: SlackRepositoryFactory.create())

        viewModel.selectedType = .fix

        #expect(viewModel.selectedType == .fix)
    }
}
