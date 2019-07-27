package web.exceptions

class UserNotFoundException: ApiException() {
    override fun message() = "The user could not be found."
    override fun httpStatus() = 404
}