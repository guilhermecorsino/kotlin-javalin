package services

import domains.Account
import domains.InMemoryAccountRepository
import services.command.TransferCommand
import services.command.CreateAccountCommand

class AccountService(
    private val manager: InMemoryAccountRepository
) {
    fun transfer(transfer: TransferCommand) {

        val giver = manager.getAccountById(transfer.giverId)
        val beneficiary = manager.getAccountById(transfer.beneficiaryId)

        giver.transfer(transfer.value, beneficiary)
    }

    fun createAccount(command: CreateAccountCommand) {
        manager.insertAccount(Account(command.id, command.balance))
    }

    fun getAllAccounts(): List<Account> {
        return manager.getAllAccounts()
    }
}
