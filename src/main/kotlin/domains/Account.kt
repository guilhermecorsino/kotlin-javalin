package domains

import exceptions.InvalidTransferException
import java.util.UUID

class Account(
    private val id: UUID,
    private var availableMoney: Double
) {
    fun transfer(moneyToBeTransferred: Double, beneficiary: Account) {
        if (moneyToBeTransferred > this.availableMoney)
            throw InvalidTransferException()

        this.availableMoney -= moneyToBeTransferred

        beneficiary.creditMoney(moneyToBeTransferred)
    }

    fun getAvailableMoney(): Double {
        return availableMoney
    }

    fun getIdentifier(): UUID {
        return id
    }

    private fun creditMoney(moneyToBeCredited: Double) {
        availableMoney += moneyToBeCredited
    }
}
