package com.xiaoli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer resultCode;
    private String resultMsg;
    private T data;

}
