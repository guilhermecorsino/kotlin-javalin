package services

import domains.Account
import domains.AccountManager
import exceptions.UserNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import requests.TransferRequest
import java.util.UUID

class AccountServiceTest {

    private val manager = AccountManager()
    private val service = AccountService(manager)

    @Test
    fun `transfers money to the beneficiary`() {
        val giverId = UUID.randomUUID()
        val beneficiaryId = UUID.randomUUID()

        manager.insertAccount(Account(giverId, 30.00))
        manager.insertAccount(Account(beneficiaryId, 10.00))

        val transaction = TransferRequest(giverId, beneficiaryId, 10.00)

        service.transfer(transaction)

        assertThat(manager.getAccountById(giverId).getAvailableMoney()).isEqualTo(20.00)
        assertThat(manager.getAccountById(beneficiaryId).getAvailableMoney()).isEqualTo(20.00)
    }

    @Test
    fun `throws UserNotFoundException when the user id was not found`() {
        val transaction = TransferRequest(UUID.randomUUID(), UUID.randomUUID(), 10.00)

        assertThatThrownBy {
            service.transfer(transaction)
        }.isInstanceOf(UserNotFoundException::class.java)
    }
}