class Account(
    private var availableMoney: Double
) {
    fun transfer(moneyToBeTransferred: Double, beneficiary: Account): Boolean {
        if (moneyToBeTransferred > this.availableMoney)
            throw InvalidTransferException()

        this.availableMoney -= moneyToBeTransferred

        beneficiary.creditMoney(moneyToBeTransferred)

        return true
    }

    fun creditMoney(moneyToBeCredited: Double) {
        availableMoney += moneyToBeCredited
    }

    fun getAvailableMoney(): Double {
        return availableMoney
    }
}
