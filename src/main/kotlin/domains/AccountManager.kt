package domains

class AccountManager {
    private var accounts: ArrayList<Account> = arrayListOf()

    fun getAccountById(id: String): Account {
        return accounts.first { it.getIdentifier() == id }
    }

    fun insertAccount(account: Account) {
        accounts.add(account)
    }
}