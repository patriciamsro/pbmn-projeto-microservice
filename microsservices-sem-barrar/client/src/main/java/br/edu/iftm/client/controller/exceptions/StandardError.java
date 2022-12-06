package br.edu.iftm.client.controller.exceptions;

public class StandardError {

    private int status;
    private String msg;
    private Long timestamp;

    public StandardError(int status, String msg, long timestamp) {
    }
}
