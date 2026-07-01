package com.ucc.clinica.exception;

public class EmailDuplicadoException extends  RuntimeException{
    public EmailDuplicadoException(String mensage){
        super(mensage);
    }
}
