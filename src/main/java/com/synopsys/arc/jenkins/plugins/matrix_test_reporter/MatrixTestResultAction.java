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

import hudson.model.AbstractBuild;
import hudson.tasks.test.AbstractTestResultAction;
import hudson.tasks.test.TestResult;

/**
 *
 * @author nenashev
 */
public class MatrixTestResultAction extends AbstractTestResultAction<AbstractTestResultAction> {

    private TestResult result;
    
    MatrixTestResultAction(TestResult result, AbstractBuild owner) {
        super(owner);
        this.result = result;
    }

    public void setResult(TestResult result) {
        this.result = result;
    }
     
    @Override
    public int getFailCount() {
        return result.getFailCount();
    }

    @Override
    public int getTotalCount() {
        return result.getTotalCount();
    }

    @Override
    public TestResult getResult() {
        return result;
    }
    
    g
}
