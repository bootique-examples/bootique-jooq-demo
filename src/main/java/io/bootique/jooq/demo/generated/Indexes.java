/*
 * This file is generated by jOOQ.
*/
package io.bootique.jooq.demo.generated;


import io.bootique.jooq.demo.generated.tables.Domain;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>testdb</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index DOMAIN_PRIMARY = Indexes0.DOMAIN_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index DOMAIN_PRIMARY = Internal.createIndex("PRIMARY", Domain.DOMAIN, new OrderField[] { Domain.DOMAIN.ID }, true);
    }
}