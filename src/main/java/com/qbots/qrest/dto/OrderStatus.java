package com.qbots.qrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum OrderStatus {

    ACTIVE,
    PRECHECK,
    DONE,
    DELETED,
    NEW




}