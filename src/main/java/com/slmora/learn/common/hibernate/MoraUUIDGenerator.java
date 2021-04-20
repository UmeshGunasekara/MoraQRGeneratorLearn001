/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/18/2020 9:56 PM
 */
package com.slmora.learn.common.hibernate;

import com.slmora.learn.common.util.UuidUtilities;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/18/2020 9:56 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/18/2020      SLMORA                Initial Code
 */
public class MoraUUIDGenerator
        implements
        IdentifierGenerator
{
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws
            HibernateException
    {
        UuidUtilities uuidUtilities = new UuidUtilities();
        UUID uuid = UUID.randomUUID();
        return uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(uuid);
    }
}
