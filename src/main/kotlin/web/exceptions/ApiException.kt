package web.exceptions

abstract class ApiException: Exception() {
    abstract fun httpStatus(): Int
    abstract fun message(): String
}