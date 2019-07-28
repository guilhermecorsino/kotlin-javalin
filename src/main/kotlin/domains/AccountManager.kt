package domains

import web.exceptions.UserAlreadyExistentException
import web.exceptions.UserNotFoundException
import java.lang.Exception

class AccountManager {
    private var accounts: ArrayList<Account> = arrayListOf()

    fun getAccountById(id: String): Account {
        try {
            return accounts.first { it.getIdentifier() == id }
        } catch (e: Exception) {
            throw UserNotFoundException()
        }
    }

    fun insertAccount(account: Account) {
        if (isARegisteredUser(account))
            throw UserAlreadyExistentException()

        accounts.add(account)
    }

    private fun isARegisteredUser(account: Account) = accounts.any { it.getIdentifier() == account.getIdentifier() }

    fun getAllAccounts(): List<Account> {
        return accounts.toList()
    }
}