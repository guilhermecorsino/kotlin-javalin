package domains

import exceptions.InvalidTransferException

class Account(
    private val id: String,
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

    fun getIdentifier(): String {
        return id
    }

    private fun debitMoney(moneyToBeTransferred: Double) {
        availableMoney -= moneyToBeTransferred
    }

    private fun creditMoney(moneyToBeCredited: Double) {
        availableMoney += moneyToBeCredited
    }

    private fun validateTransfer(moneyToBeTransferred: Double) {
        if (moneyToBeTransferred > this.availableMoney)
            throw InvalidTransferException()
    }
}
