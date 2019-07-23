package services.command

data class TransferCommand(
    val giverId: String,
    val beneficiaryId: String,
    val value: Double
)