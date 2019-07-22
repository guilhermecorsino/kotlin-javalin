package services

import domains.AccountManager
import requests.TransferRequest

class AccountService(
    private val manager: AccountManager
) {
    fun transfer(request: TransferRequest) {
        val giver = manager.getAccountByNumber(request.giverId)
        val beneficiary = manager.getAccountByNumber(request.beneficiaryId)

        giver!!.transfer(request.value, beneficiary!!)
    }
}
