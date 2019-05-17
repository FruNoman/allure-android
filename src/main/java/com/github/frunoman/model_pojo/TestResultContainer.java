
package com.github.frunoman.model_pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TestResultContainer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="children" type="{urn:model.allure.qameta.io}Ids"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descriptionHtml" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="befores" type="{urn:model.allure.qameta.io}Befores"/&gt;
 *         &lt;element name="afters" type="{urn:model.allure.qameta.io}Afters"/&gt;
 *         &lt;element name="links" type="{urn:model.allure.qameta.io}Links"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="stop" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestResultContainer", propOrder = {

})
public class TestResultContainer implements Serializable, WithLinks
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String uuid;
    @XmlElement(required = true)
    protected String name;
    @XmlElementWrapper(required = true)
    @XmlElement(name = "childId")
    protected List<String> children;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String descriptionHtml;
    @XmlElementWrapper(required = true)
    @XmlElement(name = "before")
    protected List<FixtureResult> befores;
    @XmlElementWrapper(required = true)
    @XmlElement(name = "after")
    protected List<FixtureResult> afters;
    @XmlElementWrapper(required = true)
    @XmlElement(name = "link")
    protected List<Link> links;
    @XmlAttribute(name = "start")
    protected Long start;
    @XmlAttribute(name = "stop")
    protected Long stop;

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the descriptionHtml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    /**
     * Sets the value of the descriptionHtml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionHtml(String value) {
        this.descriptionHtml = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStart(Long value) {
        this.start = value;
    }

    /**
     * Gets the value of the stop property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStop() {
        return stop;
    }

    /**
     * Sets the value of the stop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStop(Long value) {
        this.stop = value;
    }

    public List<String> getChildren() {
        if (children == null) {
            children = new ArrayList<String>();
        }
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public List<FixtureResult> getBefores() {
        if (befores == null) {
            befores = new ArrayList<FixtureResult>();
        }
        return befores;
    }

    public void setBefores(List<FixtureResult> befores) {
        this.befores = befores;
    }

    public List<FixtureResult> getAfters() {
        if (afters == null) {
            afters = new ArrayList<FixtureResult>();
        }
        return afters;
    }

    public void setAfters(List<FixtureResult> afters) {
        this.afters = afters;
    }

    public List<Link> getLinks() {
        if (links == null) {
            links = new ArrayList<Link>();
        }
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public TestResultContainer withUuid(String value) {
        setUuid(value);
        return this;
    }

    public TestResultContainer withName(String value) {
        setName(value);
        return this;
    }

    public TestResultContainer withDescription(String value) {
        setDescription(value);
        return this;
    }

    public TestResultContainer withDescriptionHtml(String value) {
        setDescriptionHtml(value);
        return this;
    }

    public TestResultContainer withStart(Long value) {
        setStart(value);
        return this;
    }

    public TestResultContainer withStop(Long value) {
        setStop(value);
        return this;
    }

    public TestResultContainer withChildren(String... values) {
        if (values!= null) {
            for (String value: values) {
                getChildren().add(value);
            }
        }
        return this;
    }

    public TestResultContainer withChildren(Collection<String> values) {
        if (values!= null) {
            getChildren().addAll(values);
        }
        return this;
    }

    public TestResultContainer withChildren(List<String> children) {
        setChildren(children);
        return this;
    }

    public TestResultContainer withBefores(FixtureResult... values) {
        if (values!= null) {
            for (FixtureResult value: values) {
                getBefores().add(value);
            }
        }
        return this;
    }

    public TestResultContainer withBefores(Collection<FixtureResult> values) {
        if (values!= null) {
            getBefores().addAll(values);
        }
        return this;
    }

    public TestResultContainer withBefores(List<FixtureResult> befores) {
        setBefores(befores);
        return this;
    }

    public TestResultContainer withAfters(FixtureResult... values) {
        if (values!= null) {
            for (FixtureResult value: values) {
                getAfters().add(value);
            }
        }
        return this;
    }

    public TestResultContainer withAfters(Collection<FixtureResult> values) {
        if (values!= null) {
            getAfters().addAll(values);
        }
        return this;
    }

    public TestResultContainer withAfters(List<FixtureResult> afters) {
        setAfters(afters);
        return this;
    }

    public TestResultContainer withLinks(Link... values) {
        if (values!= null) {
            for (Link value: values) {
                getLinks().add(value);
            }
        }
        return this;
    }

    public TestResultContainer withLinks(Collection<Link> values) {
        if (values!= null) {
            getLinks().addAll(values);
        }
        return this;
    }

    public TestResultContainer withLinks(List<Link> links) {
        setLinks(links);
        return this;
    }

}
