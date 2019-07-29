package domains

interface AccountRepository {
    fun getAccountById(id: String): Account
    fun insertAccount(account: Account)
    fun getAllAccounts(): List<Account>
}