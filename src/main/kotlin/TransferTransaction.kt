import java.util.UUID

class TransferTransaction(
    val giverId: UUID,
    val beneficiaryId: UUID,
    val value: Double
)