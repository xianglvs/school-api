package org.spring.springboot.app.base;

import org.spring.springboot.app.base.Error;

import java.util.ArrayList;
import java.util.List;

public class ErrorTools {

    private ErrorTools() {
    }

    public static List<Error> ErrorAsArrayList(Error... errors) {
        List<Error> list = new ArrayList<>();
        for (Error e : errors) {
            list.add(e);
        }
        return list;
    }
}
