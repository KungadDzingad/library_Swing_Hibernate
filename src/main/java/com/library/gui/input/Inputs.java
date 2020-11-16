package com.library.gui.input;

import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.WrongReservationInputException;

public interface Inputs<T> {
    public void input() throws WrongReservationInputException, BookNotAvailableException;
}
