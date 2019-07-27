package web.exceptions

class UserAlreadyExistentException: ApiException() {
    override fun message() = "User with this Id already exists"
    override fun httpStatus() = 422
}