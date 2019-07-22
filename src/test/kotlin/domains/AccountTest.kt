package domains

import exceptions.InvalidTransferException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import java.util.UUID

class AccountTest {

    private val uuid = UUID.randomUUID()

    @Test
    fun `throws InvalidTransfer when the accounts balance is insufficient`() {
        val account = Account(uuid, 10.00)
        val beneficiary = Account (UUID.randomUUID(), 20.00)

        assertThatThrownBy {
            account.transfer(12.00, beneficiary)
        }.isInstanceOf(InvalidTransferException::class.java)

        assertThat(account.getAvailableMoney()).isEqualTo(10.00)
    }

    @Test
    fun `decreases accounts available money when a transfer was done`() {
        val account = Account(uuid,10.00)
        val beneficiary = Account (UUID.randomUUID(),20.00)

        account.transfer(8.00, beneficiary)

        assertThat(account.getAvailableMoney()).isEqualTo(2.00)
    }

    @Test
    fun `increases transferred value in the beneficiary account`() {
        val account = Account(uuid,10.00)
        val beneficiary = Account (UUID.randomUUID(), 20.00)

        account.transfer(5.00, beneficiary)

        assertThat(beneficiary.getAvailableMoney()).isEqualTo(25.00)
    }

    @Test
    fun `throws InvalidTransfer when the beneficiary account is the same of current account number`() {
        val account = Account(uuid, 10.00)
        val beneficiary = Account (uuid, 20.00)

        assertThatThrownBy {
            account.transfer(12.00, beneficiary)
        }.isInstanceOf(InvalidTransferException::class.java)
    }
}