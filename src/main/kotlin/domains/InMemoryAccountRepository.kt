package domains

import web.exceptions.UserAlreadyExistentException
import web.exceptions.UserNotFoundException
import java.util.concurrent.ConcurrentHashMap

class InMemoryAccountRepository(
    private val accounts: ConcurrentHashMap<String, Account> = ConcurrentHashMap()
) : AccountRepository {

    override fun getAccountById(id: String): Account {
        return accounts[id] ?: throw UserNotFoundException()
    }

    override fun insertAccount(account: Account) {
        if (isARegisteredUser(account))
            throw UserAlreadyExistentException()

        accounts[account.getIdentifier()] = account
    }

    override fun getAllAccounts(): List<Account> {
        return accounts.toList().map { pair -> pair.second }
    }

    private fun isARegisteredUser(account: Account) = accounts.containsKey(account.getIdentifier())
}