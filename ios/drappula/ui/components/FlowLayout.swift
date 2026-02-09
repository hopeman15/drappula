import SwiftUI

struct FlowLayout: Layout {
    var spacing: CGFloat = 8

    func sizeThatFits(proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) -> CGSize {
        let result = arrangeSubviews(proposal: proposal, subviews: subviews)
        return result.size
    }

    func placeSubviews(in bounds: CGRect, proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) {
        let result = arrangeSubviews(proposal: proposal, subviews: subviews)
        for (index, position) in result.placements.enumerated() {
            subviews[index].place(
                at: CGPoint(
                    x: bounds.minX + position.x,
                    y: bounds.minY + position.y
                ),
                proposal: .unspecified
            )
        }
    }

    private struct LayoutResult {
        var placements: [CGPoint]
        var size: CGSize
    }

    private func arrangeSubviews(proposal: ProposedViewSize, subviews: Subviews) -> LayoutResult {
        let maxWidth = proposal.width ?? .infinity
        var rows: [[Int]] = [[]]
        var rowWidths: [CGFloat] = [0]
        var rowHeights: [CGFloat] = [0]
        var sizes: [CGSize] = []

        for (index, subview) in subviews.enumerated() {
            let size = subview.sizeThatFits(.unspecified)
            sizes.append(size)

            let additionalWidth = rows[rows.count - 1].isEmpty ? size.width : spacing + size.width

            if rowWidths[rowWidths.count - 1] + additionalWidth > maxWidth && !rows[rows.count - 1].isEmpty {
                rows.append([index])
                rowWidths.append(size.width)
                rowHeights.append(size.height)
            } else {
                rows[rows.count - 1].append(index)
                rowWidths[rowWidths.count - 1] += additionalWidth
                rowHeights[rowHeights.count - 1] = max(rowHeights[rowHeights.count - 1], size.height)
            }
        }

        var placements: [CGPoint] = Array(repeating: .zero, count: subviews.count)
        var currentY: CGFloat = 0

        for (rowIndex, row) in rows.enumerated() {
            let rowWidth = rowWidths[rowIndex]
            let offsetX = (maxWidth - rowWidth) / 2
            var currentX = offsetX

            for index in row {
                placements[index] = CGPoint(x: currentX, y: currentY)
                currentX += sizes[index].width + spacing
            }

            currentY += rowHeights[rowIndex] + spacing
        }

        let totalHeight = currentY - (rows.isEmpty ? 0 : spacing)
        return LayoutResult(
            placements: placements,
            size: CGSize(width: maxWidth, height: max(0, totalHeight))
        )
    }
}
