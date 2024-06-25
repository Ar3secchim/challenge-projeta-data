package com.challenge.adm.challengeprojetadata.infra.expection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadRequestClient extends Exception {
    private String message;
}