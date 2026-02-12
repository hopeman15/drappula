import shared
import SwiftUI

struct FeedbackView: View {
    @Environment(\.drappulaTheme) private var theme
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = FeedbackViewModel.create()

    private let titleMaxLength = 30
    private let messageMaxLength = 300

    private let feedbackTypes: [(String, Feedback.Type_)] = [
        ("Enhancement", .enhancement),
        ("Feature Request", .feature),
        ("Fix", .fix),
        ("Other", .other)
    ]

    var body: some View {
        ZStack {
            theme.gradients.background
                .ignoresSafeArea()

            VStack(alignment: .leading, spacing: 0) {
                FeedbackHeader(onBack: { dismiss() })

                ScrollView {
                    VStack(spacing: 16) {
                        FeedbackTypePicker(
                            selectedType: $viewModel.selectedType,
                            feedbackTypes: feedbackTypes
                        )

                        FeedbackTextField(
                            text: $viewModel.title,
                            placeholder: "Title",
                            maxLength: titleMaxLength
                        )

                        FeedbackTextArea(
                            text: $viewModel.message,
                            placeholder: "Message",
                            maxLength: messageMaxLength
                        )

                        FeedbackSubmitButton(
                            isEnabled: viewModel.isComplete && !viewModel.isLoading,
                            onSubmit: { viewModel.submit() }
                        )
                    }
                    .padding(.top, 16)
                }
            }
        }
        .navigationBarHidden(true)
        .toolbar(.hidden, for: .tabBar)
        .alert(
            "Upload Error",
            isPresented: Binding(
                get: { viewModel.error != nil },
                set: { if !$0 { viewModel.clearError() } }
            )
        ) {
            Button("Cancel", role: .cancel) {
                viewModel.clearError()
            }
            Button("Retry") {
                viewModel.submit()
            }
        } message: {
            Text("Oh no, something happened during the upload. Would you like to retry?")
        }
        .onChange(of: viewModel.isSubmitted) { _, isSubmitted in
            if isSubmitted {
                dismiss()
            }
        }
    }
}

struct FeedbackView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            FeedbackView()
        }
        .drappulaTheme()
    }
}
