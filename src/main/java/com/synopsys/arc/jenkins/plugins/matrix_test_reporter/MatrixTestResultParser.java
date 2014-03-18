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
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.tasks.junit.CaseResult;
import hudson.tasks.junit.ClassResult;
import hudson.tasks.test.SimpleCaseResult;
import hudson.tasks.test.TestObject;
import hudson.tasks.test.TestResult;
import hudson.tasks.test.TestResultParser;
import java.io.IOException;

/**
 * Parses build results for the test.
 */
@Extension
public class MatrixTestResultParser extends TestResultParser {

    @Override
    public String getDisplayName() {
        return Messages.MatrixTestResultParser_displayName();
    }
     
    @Override
    public TestResult parse(String testResultLocations, AbstractBuild build, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        TestObject obj = new SimpleCaseResult();
        return new MatrixTestCaseResult(build);
    }
    
}
