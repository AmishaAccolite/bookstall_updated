package com.BookStall.bookstall.Exceptions;

public class NoObjectFoundException extends RuntimeException
{
    public NoObjectFoundException(String message)
    {
        super(message);
    }
}