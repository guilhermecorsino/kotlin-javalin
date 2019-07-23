package test.domains

import domains.Account
import domains.AccountManager
import exceptions.UserAlreadyExistentException
import exceptions.UserNotFoundException
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
    fun `throws UserAlreadyExistentException when trying to insert an user with an existent Id`() {
        val accountManager = AccountManager()

        accountManager.insertAccount(Account("1",10.00))

        assertThatThrownBy {
            accountManager.insertAccount(Account("1",10.00))
        }.isInstanceOf(UserAlreadyExistentException::class.java)
    }

    @Test
    fun `throws UserNotFoundException when account was not found`() {
        val accountManager = AccountManager()

        assertThatThrownBy { accountManager.getAccountById("1") }
            .isInstanceOf(UserNotFoundException::class.java)
    }

    @Test
    fun `returns all created accounts`() {
        val accountManager = AccountManager()

        accountManager.insertAccount(Account("1",10.00))
        accountManager.insertAccount(Account("2",15.00))
        accountManager.insertAccount(Account("3",20.00))

        val accounts = accountManager.getAllAccounts()

        assertThat(accounts.size).isEqualTo(3)
    }
}