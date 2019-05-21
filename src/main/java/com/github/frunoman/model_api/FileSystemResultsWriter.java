package com.github.frunoman.model_api;

import com.github.frunoman.model.Allure2ModelJackson;
import com.github.frunoman.model_pojo.TestResult;
import com.github.frunoman.model_pojo.TestResultContainer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java8.util.Objects;

import static com.github.frunoman.model_api.AllureUtils.generateTestResultContainerName;
import static com.github.frunoman.model_api.AllureUtils.generateTestResultName;


/**
 * @author charlie (Dmitry Baev).
 */
public class FileSystemResultsWriter implements AllureResultsWriter {

    private final String outputDirectory;

    private final ObjectMapper mapper;

    public FileSystemResultsWriter(String outputDirectory) {
        this.outputDirectory = outputDirectory;
        this.mapper = Allure2ModelJackson.createMapper();
    }

    @Override
    public void write(TestResult testResult)  {
        final String testResultName = Objects.isNull(testResult.getUuid())
                ? generateTestResultName()
                : generateTestResultName(testResult.getUuid());
        createDirectories(outputDirectory);
        File file = new File(outputDirectory+File.separator+testResultName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
            mapper.writeValue(os, testResult);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not write Allure test result", e);
        }
    }

    @Override
    public void write(TestResultContainer testResultContainer) {
        final String testResultContainerName = Objects.isNull(testResultContainer.getUuid())
                ? generateTestResultContainerName()
                : generateTestResultContainerName(testResultContainer.getUuid());
        createDirectories(outputDirectory);
        File file = new File(outputDirectory+File.separator+testResultContainerName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
            mapper.writeValue(os, testResultContainer);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not write Allure test result container", e);
        }
    }

    @Override
    public void write(String source, InputStream attachment)  {
        createDirectories(outputDirectory);
        File file = new File(outputDirectory+File.separator+source);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try  {
            com.github.frunoman.allure.util.Objects.copyFileUsingStream(attachment, file);
        } catch (IOException e) {
            throw new AllureResultsWriteException("Could not write Allure attachment", e);
        }
    }

    private void createDirectories(String directory) {
        try {
            File file = new File(directory);
            if(!file.exists()){
                file.mkdir();
            }
        } catch (Exception e) {
            throw new AllureResultsWriteException("Could not create Allure results directory", e);
        }
    }
}
