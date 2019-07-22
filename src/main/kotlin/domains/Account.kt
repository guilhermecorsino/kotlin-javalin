package domains

import exceptions.InvalidTransferException
import java.util.UUID

class Account(
    private val id: UUID,
    private var availableMoney: Double
) {
    fun transfer(moneyToBeTransferred: Double, beneficiary: Account) {
        validateTransfer(moneyToBeTransferred)

        debitMoney(moneyToBeTransferred)

        beneficiary.creditMoney(moneyToBeTransferred)
    }

    fun getAvailableMoney(): Double {
        return availableMoney
    }

    fun getIdentifier(): UUID {
        return id
    }

    private fun debitMoney(moneyToBeTransferred: Double) {
        this.availableMoney -= moneyToBeTransferred
    }

    private fun creditMoney(moneyToBeCredited: Double) {
        availableMoney += moneyToBeCredited
    }

    private fun validateTransfer(moneyToBeTransferred: Double) {
        if (moneyToBeTransferred > this.availableMoney)
            throw InvalidTransferException()
    }
}
