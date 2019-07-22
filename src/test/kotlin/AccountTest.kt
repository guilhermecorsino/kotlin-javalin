import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Assert.assertTrue
import org.junit.Test

class AccountTest {

    @Test
    fun `does transfer when account has the value to be transacted`() {
        val account = Account(10.00)
        val beneficiary = Account (20.00)

        val isTransferValid = account.transfer(10.00, beneficiary)

        assertTrue(isTransferValid)
    }

    @Test
    fun `throws InvalidTransfer when the accounts balance is insufficient`() {
        val account = Account(10.00)
        val beneficiary = Account (20.00)

        assertThatThrownBy {
            account.transfer(12.00, beneficiary)
        }.isInstanceOf(InvalidTransferException::class.java)

        assertThat(account.getAvailableMoney()).isEqualTo(10.00)
    }

    @Test
    fun `decreases accounts available money when a transfer was done`() {
        val account = Account(10.00)
        val beneficiary = Account (20.00)

        account.transfer(8.00, beneficiary)

        assertThat(account.getAvailableMoney()).isEqualTo(2.00)
    }

    @Test
    fun `increases transferred value in the beneficiary account`() {
        val account = Account(10.00)
        val beneficiary = Account (20.00)

        account.transfer(5.00, beneficiary)

        assertThat(beneficiary.getAvailableMoney()).isEqualTo(25.00)
    }
}