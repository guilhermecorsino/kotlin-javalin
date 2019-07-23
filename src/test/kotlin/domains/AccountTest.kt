package test.domains

import domains.Account
import exceptions.InvalidTransferException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class AccountTest {

    private val id = "1"

    @Test
    fun `throws InvalidTransfer when the accounts balance is insufficient`() {
        val account = Account(id, 10.00)
        val beneficiary = Account ("5", 20.00)

        assertThatThrownBy {
            account.transfer(12.00, beneficiary)
        }.isInstanceOf(InvalidTransferException::class.java)

        assertThat(account.getAvailableMoney()).isEqualTo(10.00)
    }

    @Test
    fun `decreases accounts available money when a transfer was done`() {
        val account = Account(id,10.00)
        val beneficiary = Account ("5",20.00)

        account.transfer(8.00, beneficiary)

        assertThat(account.getAvailableMoney()).isEqualTo(2.00)
    }

    @Test
    fun `increases transferred value in the beneficiary account`() {
        val account = Account(id,10.00)
        val beneficiary = Account ("5", 20.00)

        account.transfer(5.00, beneficiary)

        assertThat(beneficiary.getAvailableMoney()).isEqualTo(25.00)
    }

    @Test
    fun `throws InvalidTransfer when the beneficiary account is the same of current account number`() {
        val account = Account(id, 10.00)
        val beneficiary = Account (id, 20.00)

        assertThatThrownBy {
            account.transfer(12.00, beneficiary)
        }.isInstanceOf(InvalidTransferException::class.java)
    }
}