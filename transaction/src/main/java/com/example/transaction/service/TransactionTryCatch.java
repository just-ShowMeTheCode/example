package com.example.transaction.service;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2315:46
 */
public interface TransactionTryCatch  {
    /**
     * catch 中代码块处于同一个事务中，操作sql 会被回滚
     */
    void testTransactionTryCatchRequired();
    /**
     * catch 中代码块处于不在一个事务中，操作sql 正常commit
     */
    void testTransactionTryCatchRequiredNew();
    void truncateTable();
}
