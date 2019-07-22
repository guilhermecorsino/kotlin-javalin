package requests

import java.util.UUID

class TransferRequest(
    val giverId: UUID,
    val beneficiaryId: UUID,
    val value: Double
)