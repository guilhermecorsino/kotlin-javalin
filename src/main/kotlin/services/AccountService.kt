package services

import domains.AccountManager
import exceptions.UserNotFoundException
import requests.TransferCommand

class AccountService(
    private val manager: AccountManager
) {
    fun transfer(transfer: TransferCommand) {
        try {
            val giver = manager.getAccountById(transfer.giverId)
            val beneficiary = manager.getAccountById(transfer.beneficiaryId)

            giver.transfer(transfer.value, beneficiary)
        }
        catch (e: Exception) {
            throw UserNotFoundException()
        }
    }
}
