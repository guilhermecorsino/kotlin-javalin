package test.domains

import domains.Account
import domains.AccountManager
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class AccountManagerTest {

    @Test
    fun `inserts a new account`() {
        val accountManager = AccountManager()
        val accountId = "123"

        accountManager.insertAccount(Account(accountId,10.00))

        val account = accountManager.getAccountById(accountId)

        assertThat(account.getIdentifier()).isEqualTo(accountId)
    }

    @Test
    fun `throws NoSuchElementException when account was not found`() {
        val accountManager = AccountManager()

        assertThatThrownBy { accountManager.getAccountById("1") }
    }
}