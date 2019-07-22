import java.util.UUID

class AccountManager {
    private var accounts: ArrayList<Account> = arrayListOf()

    fun getAccountByNumber(accountsNumber: UUID): Account? {
        return accounts.firstOrNull { it.getIdentifier() == accountsNumber }
    }

    fun insertAccount(account: Account) {
        accounts.add(account)
    }
}