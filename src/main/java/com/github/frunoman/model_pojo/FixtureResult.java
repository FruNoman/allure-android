
package com.github.frunoman.model_pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FixtureResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FixtureResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:model.allure.qameta.io}ExecutableItem"&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixtureResult")
public class FixtureResult
    extends ExecutableItem
    implements Serializable
{

    private final static long serialVersionUID = 1L;

    @Override
    public FixtureResult withName(String value) {
        setName(value);
        return this;
    }

    @Override
    public FixtureResult withStatus(Status value) {
        setStatus(value);
        return this;
    }

    @Override
    public FixtureResult withStatusDetails(StatusDetails value) {
        setStatusDetails(value);
        return this;
    }

    @Override
    public FixtureResult withStage(Stage value) {
        setStage(value);
        return this;
    }

    @Override
    public FixtureResult withDescription(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public FixtureResult withDescriptionHtml(String value) {
        setDescriptionHtml(value);
        return this;
    }

    @Override
    public FixtureResult withStart(Long value) {
        setStart(value);
        return this;
    }

    @Override
    public FixtureResult withStop(Long value) {
        setStop(value);
        return this;
    }

    @Override
    public FixtureResult withSteps(StepResult... values) {
        if (values!= null) {
            for (StepResult value: values) {
                getSteps().add(value);
            }
        }
        return this;
    }

    @Override
    public FixtureResult withSteps(Collection<StepResult> values) {
        if (values!= null) {
            getSteps().addAll(values);
        }
        return this;
    }

    @Override
    public FixtureResult withSteps(List<StepResult> steps) {
        setSteps(steps);
        return this;
    }

    @Override
    public FixtureResult withAttachments(Attachment... values) {
        if (values!= null) {
            for (Attachment value: values) {
                getAttachments().add(value);
            }
        }
        return this;
    }

    @Override
    public FixtureResult withAttachments(Collection<Attachment> values) {
        if (values!= null) {
            getAttachments().addAll(values);
        }
        return this;
    }

    @Override
    public FixtureResult withAttachments(List<Attachment> attachments) {
        setAttachments(attachments);
        return this;
    }

    @Override
    public FixtureResult withParameters(Parameter... values) {
        if (values!= null) {
            for (Parameter value: values) {
                getParameters().add(value);
            }
        }
        return this;
    }

    @Override
    public FixtureResult withParameters(Collection<Parameter> values) {
        if (values!= null) {
            getParameters().addAll(values);
        }
        return this;
    }

    @Override
    public FixtureResult withParameters(List<Parameter> parameters) {
        setParameters(parameters);
        return this;
    }

}
