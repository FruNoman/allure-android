
package com.github.frunoman.model_pojo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Stage.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Stage"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="scheduled"/&gt;
 *     &lt;enumeration value="running"/&gt;
 *     &lt;enumeration value="finished"/&gt;
 *     &lt;enumeration value="pending"/&gt;
 *     &lt;enumeration value="interrupted"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Stage")
@XmlEnum
public enum Stage {

    @XmlEnumValue("scheduled")
    SCHEDULED("scheduled"),
    @XmlEnumValue("running")
    RUNNING("running"),
    @XmlEnumValue("finished")
    FINISHED("finished"),
    @XmlEnumValue("pending")
    PENDING("pending"),
    @XmlEnumValue("interrupted")
    INTERRUPTED("interrupted");
    private final String value;

    Stage(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Stage fromValue(String v) {
        for (Stage c: Stage.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
