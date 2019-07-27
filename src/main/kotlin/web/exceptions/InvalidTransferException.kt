package web.exceptions

class InvalidTransferException: ApiException() {
    override fun message() = "This transfer is invalid."
    override fun httpStatus() = 422
}
