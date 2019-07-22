package services

import domains.Account
import domains.AccountManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import requests.TransferRequest
import services.AccountService
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

        assertThat(manager.getAccountByNumber(giverId)?.getAvailableMoney()).isEqualTo(20.00)
        assertThat(manager.getAccountByNumber(beneficiaryId)?.getAvailableMoney()).isEqualTo(20.00)
    }
}