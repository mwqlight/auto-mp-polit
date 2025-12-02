package com.autodag.entity;

public enum DagStatus {
    PENDING,     // 待执行
    RUNNING,     // 运行中
    SUCCESS,     // 成功
    FAILED,      // 失败
    CANCELLED    // 已取消
}