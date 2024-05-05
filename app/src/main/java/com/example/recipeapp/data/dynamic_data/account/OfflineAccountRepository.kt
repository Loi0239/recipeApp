package com.example.recipeapp.data.dynamic_data.account

import kotlinx.coroutines.flow.Flow

class OfflineAccountRepository(private val accountDao: AccountDao):AccountRepository {
    override fun getAccount(id: Int): Flow<Account> = accountDao.getAccount(id)
    override fun checkAccount(userName: String, password: String): Int =
        accountDao.checkAccount(userName, password)

    override fun checkUserName(userName: String): Int =
        accountDao.checkUserName(userName)

    override fun getIdUser(userName: String, password: String): Int =
        accountDao.getIdUser(userName, password)

    override suspend fun getLastInsertId(): Int = accountDao.getLastInsertId()
    override suspend fun insertAccount(account: Account) = accountDao.insertAccount(account)
}