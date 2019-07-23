package domains

import exceptions.UserNotFoundException
import java.lang.Exception

class AccountManager {
    private var accounts: ArrayList<Account> = arrayListOf()

    fun getAccountById(id: String): Account {
        try {
            return accounts.first { it.getIdentifier() == id }
        } catch(e: Exception) {
            throw UserNotFoundException()
        }
    }

    fun insertAccount(account: Account) {
        accounts.add(account)
    }
}