class AccountService(
    private val manager: AccountManager
) {
    fun transfer(transaction: TransferTransaction) {
        val giver = manager.getAccountByNumber(transaction.giverId)
        val beneficiary = manager.getAccountByNumber(transaction.beneficiaryId)

        giver!!.transfer(transaction.value, beneficiary!!)
    }
}
