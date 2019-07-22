package domains

import java.util.UUID

class AccountManager {
    private var accounts: ArrayList<Account> = arrayListOf()

    fun getAccountById(id: UUID): Account? {
        return accounts.firstOrNull { it.getIdentifier() == id }
    }

    fun insertAccount(account: Account) {
        accounts.add(account)
    }
}