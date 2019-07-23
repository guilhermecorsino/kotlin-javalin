package requests

data class TransferCommand(
    val giverId: String,
    val beneficiaryId: String,
    val value: Double
)