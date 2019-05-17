
package com.github.frunoman.model_pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusDetails"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="known" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="muted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="flaky" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="trace" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusDetails", propOrder = {

})
public class StatusDetails implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected boolean known;
    protected boolean muted;
    protected boolean flaky;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected String trace;

    /**
     * Gets the value of the known property.
     * 
     */
    public boolean isKnown() {
        return known;
    }

    /**
     * Sets the value of the known property.
     * 
     */
    public void setKnown(boolean value) {
        this.known = value;
    }

    /**
     * Gets the value of the muted property.
     * 
     */
    public boolean isMuted() {
        return muted;
    }

    /**
     * Sets the value of the muted property.
     * 
     */
    public void setMuted(boolean value) {
        this.muted = value;
    }

    /**
     * Gets the value of the flaky property.
     * 
     */
    public boolean isFlaky() {
        return flaky;
    }

    /**
     * Sets the value of the flaky property.
     * 
     */
    public void setFlaky(boolean value) {
        this.flaky = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the trace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrace() {
        return trace;
    }

    /**
     * Sets the value of the trace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrace(String value) {
        this.trace = value;
    }

    public StatusDetails withKnown(boolean value) {
        setKnown(value);
        return this;
    }

    public StatusDetails withMuted(boolean value) {
        setMuted(value);
        return this;
    }

    public StatusDetails withFlaky(boolean value) {
        setFlaky(value);
        return this;
    }

    public StatusDetails withMessage(String value) {
        setMessage(value);
        return this;
    }

    public StatusDetails withTrace(String value) {
        setTrace(value);
        return this;
    }

}
