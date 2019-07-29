package domains

import web.exceptions.UserAlreadyExistentException
import web.exceptions.UserNotFoundException
import java.lang.Exception

class InMemoryAccountRepository: AccountRepository {
    private var accounts: ArrayList<Account> = arrayListOf()

    override fun getAccountById(id: String): Account {
        try {
            return accounts.first { it.getIdentifier() == id }
        } catch (e: Exception) {
            throw UserNotFoundException()
        }
    }

    override fun insertAccount(account: Account) {
        if (isARegisteredUser(account))
            throw UserAlreadyExistentException()

        accounts.add(account)
    }

    override fun getAllAccounts(): List<Account> {
        return accounts.toList()
    }

    private fun isARegisteredUser(account: Account) = accounts.any { it.getIdentifier() == account.getIdentifier() }
}