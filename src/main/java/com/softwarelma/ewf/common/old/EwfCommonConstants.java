package com.softwarelma.ewf.common.old;

import java.io.Serializable;

public abstract class EwfCommonConstants implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * enables exception logging
     */
    public static boolean SHOW_EXCEPTIONS = true;

    /*
     * OPENING AND CLOSING CONTAINED STRINGS
     */
    public static final String CONTAINED_STRING_OPEN = "{";
    public static final String CONTAINED_STRING_CLOSE = "}";

    public static final String ENTITY_NAME_UNUSED = "unused";

    /*
     * TABLE PREFIXES
     */

    // see also EwfEntityTables

    /*
     * COLUMN PREFIXES
     */

    // see also EwfEntityColumns

}
