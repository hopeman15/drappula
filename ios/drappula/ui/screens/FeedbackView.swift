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
                // Header
                HStack {
                    Button {
                        dismiss()
                    } label: {
                        Image(systemName: "arrow.left")
                            .foregroundColor(theme.colors.onBackground)
                            .padding(8)
                    }

                    Text("Feedback")
                        .font(theme.typography.headline)
                        .fontWeight(.bold)
                        .foregroundColor(theme.colors.onBackground)

                    Spacer()
                }
                .padding(.vertical, 8)
                .padding(.horizontal, 8)

                ScrollView {
                    VStack(spacing: 16) {
                        // Type Picker
                        HStack {
                            Text("Type")
                                .font(theme.typography.body)
                                .foregroundColor(theme.colors.onBackground)

                            Spacer()

                            Picker("Type", selection: $viewModel.selectedType) {
                                ForEach(feedbackTypes, id: \.0) { label, type in
                                    Text(label).tag(type)
                                }
                            }
                            .pickerStyle(.menu)
                            .tint(theme.colors.onBackground)
                        }
                        .padding(.horizontal, 16)

                        // Title Field
                        VStack(alignment: .leading, spacing: 4) {
                            TextField(
                                "Title",
                                text: $viewModel.title,
                                prompt: Text("Title")
                                    .foregroundColor(theme.colors.onBackground.opacity(0.5))
                            )
                                .font(theme.typography.body)
                                .foregroundColor(theme.colors.onBackground)
                                .padding(10)
                                .background(
                                    RoundedRectangle(cornerRadius: 6)
                                        .stroke(
                                            theme.colors.onBackground.opacity(0.5),
                                            lineWidth: 1
                                        )
                                )
                                .onChange(of: viewModel.title) { _, newValue in
                                    if newValue.count > titleMaxLength {
                                        viewModel.title = String(newValue.prefix(titleMaxLength))
                                    }
                                }

                            Text("\(viewModel.title.count)/\(titleMaxLength)")
                                .font(theme.typography.caption)
                                .foregroundColor(theme.colors.onBackground)
                        }
                        .padding(.horizontal, 16)

                        // Message Field
                        VStack(alignment: .leading, spacing: 4) {
                            ZStack(alignment: .topLeading) {
                                if viewModel.message.isEmpty {
                                    Text("Message")
                                        .font(theme.typography.body)
                                        .foregroundColor(theme.colors.onBackground.opacity(0.5))
                                        .padding(.horizontal, 11)
                                        .padding(.vertical, 14)
                                }
                                TextEditor(text: $viewModel.message)
                                    .font(theme.typography.body)
                                    .frame(minHeight: 150)
                                    .scrollContentBackground(.hidden)
                                    .padding(6)
                                    .foregroundColor(theme.colors.onBackground)
                                    .onChange(of: viewModel.message) { _, newValue in
                                        if newValue.count > messageMaxLength {
                                            viewModel.message = String(newValue.prefix(messageMaxLength))
                                        }
                                    }
                            }
                            .background(
                                RoundedRectangle(cornerRadius: 6)
                                    .stroke(
                                        theme.colors.onBackground.opacity(0.5),
                                        lineWidth: 1
                                    )
                            )

                            Text("\(viewModel.message.count)/\(messageMaxLength)")
                                .font(theme.typography.caption)
                                .foregroundColor(theme.colors.onBackground)
                        }
                        .padding(.horizontal, 16)

                        // Submit Button
                        Button {
                            viewModel.submit()
                        } label: {
                            Text("Submit")
                                .font(theme.typography.button)
                                .foregroundColor(
                                    viewModel.isComplete
                                        ? theme.colors.background
                                        : theme.colors.background.opacity(0.3)
                                )
                                .padding(.horizontal, 24)
                                .padding(.vertical, 12)
                                .background(
                                    viewModel.isComplete
                                        ? theme.colors.onBackground
                                        : theme.colors.onBackground.opacity(0.3)
                                )
                                .clipShape(RoundedRectangle(cornerRadius: 8))
                        }
                        .disabled(!viewModel.isComplete || viewModel.isLoading)
                        .padding(.top, 24)
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
