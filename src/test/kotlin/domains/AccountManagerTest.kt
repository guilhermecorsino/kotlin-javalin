package domains

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import java.util.UUID

class AccountManagerTest {

    @Test
    fun `inserts a new account`() {
        val accountManager = AccountManager()
        val accountId = UUID.randomUUID()

        accountManager.insertAccount(Account(accountId,10.00))

        val account = accountManager.getAccountById(accountId)

        assertThat(account.getIdentifier()).isEqualTo(accountId)
    }

    @Test
    fun `throws NoSuchElementException when account was not found`() {
        val accountManager = AccountManager()

        assertThatThrownBy { accountManager.getAccountById(UUID.randomUUID()) }
    }
}