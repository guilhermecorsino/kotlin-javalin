package web.requests

data class TransferRequest(
    val beneficiaryId: String,
    val value: Double
)
