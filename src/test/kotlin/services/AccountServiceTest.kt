package services

import domains.Account
import domains.InMemoryAccountRepository
import web.exceptions.InvalidTransferException
import web.exceptions.UserNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import services.command.TransferCommand
import services.command.CreateAccountCommand

class AccountServiceTest {

    private val manager = InMemoryAccountRepository()
    private val service = AccountService(manager)

    @Test
    fun `transfers money to the beneficiary`() {
        val giverId = "123"
        val beneficiaryId = "55"

        manager.insertAccount(Account(giverId, 30.00))
        manager.insertAccount(Account(beneficiaryId, 10.00))

        val transaction = TransferCommand(giverId, beneficiaryId, 10.00)

        service.transfer(transaction)

        assertThat(manager.getAccountById(giverId).getAvailableMoney()).isEqualTo(20.00)
        assertThat(manager.getAccountById(beneficiaryId).getAvailableMoney()).isEqualTo(20.00)
    }

    @Test
    fun `throws UserNotFoundException when the user id was not found`() {
        val transaction = TransferCommand("1", "2", 10.00)

        assertThatThrownBy {
            service.transfer(transaction)
        }.isInstanceOf(UserNotFoundException::class.java)
    }

    @Test
    fun `throws InvalidTransferException when the user has no money to transfer`() {
        manager.insertAccount(Account("2", 1.00))
        manager.insertAccount(Account("4", 60.00))

        val transaction = TransferCommand("2", "4", 50.00)

        assertThatThrownBy {
            service.transfer(transaction)
        }.isInstanceOf(InvalidTransferException::class.java)
    }

    @Test
    fun `inserts account successfully`() {
        val id = "123"
        val command = CreateAccountCommand(id, 10.00)

        service.createAccount(command)

        assertThat(manager.getAccountById(id).getIdentifier()).isEqualTo(id)
    }

    @Test
    fun `gets all created accounts`() {
        val giverId = "123"
        val beneficiaryId = "55"

        manager.insertAccount(Account(giverId, 30.00))
        manager.insertAccount(Account(beneficiaryId, 10.00))

        val accounts = service.getAllAccounts()

        assertThat(accounts.size).isEqualTo(2)
    }
}