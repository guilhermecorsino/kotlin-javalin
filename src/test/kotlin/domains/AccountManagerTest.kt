package domains

import domains.Account
import domains.AccountManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.UUID

class AccountManagerTest {

    @Test
    fun `inserts a new account`() {
        val accountManager = AccountManager()
        val accountId = UUID.randomUUID()

        accountManager.insertAccount(Account(accountId,10.00))

        val account = accountManager.getAccountByNumber(accountId)

        assertThat(account?.getIdentifier()).isEqualTo(accountId)
    }

    @Test
    fun `returns null when account was not found`() {
        val accountManager = AccountManager()

        val account = accountManager.getAccountByNumber(UUID.randomUUID())

        assertThat(account).isNull()
    }
}