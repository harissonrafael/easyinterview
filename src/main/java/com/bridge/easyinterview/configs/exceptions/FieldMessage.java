package com.bridge.easyinterview.configs.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Data
@AllArgsConstructor
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 7984785922845982683L;

    private String fieldName;

    private String message;

}
