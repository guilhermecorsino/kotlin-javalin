package services

import domains.AccountManager
import requests.TransferRequest

class AccountService(
    private val manager: AccountManager
) {
    fun transfer(request: TransferRequest) {
        val giver = manager.getAccountById(request.giverId)
        val beneficiary = manager.getAccountById(request.beneficiaryId)

        giver!!.transfer(request.value, beneficiary!!)
    }
}
