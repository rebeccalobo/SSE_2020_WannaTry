package com.SSE2020.WannaTry.service;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class IDGenerator implements IdentifierGenerator {

    public static final String generatorName = "idGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return new Random().nextInt(90000000) + 10000000;
    }
}
