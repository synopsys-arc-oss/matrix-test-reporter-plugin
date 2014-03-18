/*
 * The MIT License
 *
 * Copyright 2014 nenashev.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.synopsys.arc.jenkins.plugins.matrix_test_reporter;

import hudson.Extension;
import hudson.Launcher;
import hudson.matrix.MatrixAggregatable;
import hudson.matrix.MatrixAggregator;
import hudson.matrix.MatrixBuild;
import hudson.matrix.MatrixProject;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import hudson.tasks.test.TestResult;
import hudson.tasks.test.TestResultAggregator;
import java.io.IOException;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 *
 * @author nenashev
 */
public class MatrixTestPublisher extends Recorder implements MatrixAggregatable {

    private final String testName;
    
    @DataBoundConstructor
    public MatrixTestPublisher(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }
    
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        MatrixTestResultAction action = build.getAction(MatrixTestResultAction.class); 
        TestResult result = new MatrixTestCaseResult(build);
        
        if (action == null) {
            action = new MatrixTestResultAction(result, build);
            build.addAction(action);
        } else {
            action.setResult(result);
        }
            
        return true;
    }
    
    @Override
    public MatrixAggregator createAggregator(MatrixBuild build,
            Launcher launcher, BuildListener listener) {
        return new TestResultAggregator(build, launcher, listener);
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return DESCRIPTOR;
    }
   
    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();
    
    public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return (jobType == MatrixProject.class);
        }

        @Override
        public String getDisplayName() {
            return "TODO: Publisher";
        }      
    }
    
}
