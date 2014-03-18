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
import hudson.model.Result;
import hudson.tasks.test.SimpleCaseResult;
import hudson.tasks.test.TestObject;
import hudson.tasks.test.TestResult;

/**
 * Implements a test result for {@link MatrixTestResultParser}.
 * @author nenashev
 */
public class MatrixTestCaseResult extends SimpleCaseResult {
    
    private final AbstractBuild owner;
      
    MatrixTestCaseResult(AbstractBuild owner) {
        this.owner = owner;
    }

    @Override
    public AbstractBuild<?, ?> getOwner() {
        return owner;
    }

    @Override
    public TestObject getParent() {
        return null;
    }

    @Override
    public TestResult findCorrespondingResult(String id) {
        return null;
    }

    @Override
    public boolean isPassed() {
        return owner.getResult() == Result.SUCCESS;
    }

    @Override
    public boolean isFailed() {
        return (owner.getResult() == Result.FAILURE) || (owner.getResult() == Result.UNSTABLE);
    }
    
    @Override
    public boolean isSkipped() {
        return (owner.getResult() == Result.ABORTED) || (owner.getResult() == Result.NOT_BUILT);
    }
    
    @Override
    public int getFailCount() {
        return isFailed() ? 1 : 0;
    }

    @Override
    public int getPassCount() {
        return isPassed() ? 1 : 0;
    }

    @Override
    public float getDuration() {
        return owner.getDuration();
    }    

    @Override
    public Result getBuildResult() {
        return owner.getResult();
    }
    
    
    
    
}
