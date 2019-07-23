package services

import domains.Account
import domains.AccountManager
import requests.TransferCommand

class AccountService(
    private val manager: AccountManager
) {
    fun transfer(transfer: TransferCommand) {

        val giver = manager.getAccountById(transfer.giverId)
        val beneficiary = manager.getAccountById(transfer.beneficiaryId)

        giver.transfer(transfer.value, beneficiary)
    }

    fun createAccount(command: CreateAccountCommand) {
        manager.insertAccount(Account(command.id, command.balance))
    }
}
