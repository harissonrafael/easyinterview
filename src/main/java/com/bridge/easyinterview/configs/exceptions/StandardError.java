package com.bridge.easyinterview.configs.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Data
@AllArgsConstructor
public class StandardError implements Serializable {

    private static final long serialVersionUID = -2467647473895454126L;

    private Long timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

}
