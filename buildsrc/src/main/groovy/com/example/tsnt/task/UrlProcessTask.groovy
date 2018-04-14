package com.example.tsnt.task

import org.gradle.api.DefaultTask
import org.gradle.api.internal.tasks.options.Option
import org.gradle.api.internal.tasks.options.OptionValues
import org.gradle.api.tasks.TaskAction;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-03-21 20:08
 * @Description:
 */

class UrlProcessTask extends DefaultTask {
    private String url;
    private OutputType outputType;

    @Option(option = "url", description = "Configures the URL to be write to the output.")
    void setUrl(String url) {
        this.url = url;
    }

    @Option(option = "output-type", description = "Configures the output type.")
    void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    @OptionValues("output-type")
    List<OutputType> getAvailableOutputTypes() {
        return new ArrayList<OutputType>(Arrays.asList(OutputType.values()));
    }

    @TaskAction
    void process() {
        getLogger().quiet("Writing out the URL reponse from '{}' to '{}'", url, outputType);

        // retrieve content from URL and write to output
    }

    private static enum OutputType {
        CONSOLE, FILE
    }
}
