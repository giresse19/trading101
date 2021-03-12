package com.appsdeveloperblog.app.ws.mobileappws.Exceptions;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.util.Collection;

public class UnrecognisedPropException extends UnrecognizedPropertyException {
    public UnrecognisedPropException(JsonParser p, String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        super(p, msg, loc, referringClass, propName, propertyIds);
    }
}
