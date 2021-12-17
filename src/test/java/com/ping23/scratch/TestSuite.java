package com.ping23.scratch;

import com.ping23.scratch.commercehub_test.TestSuperStack;
import com.ping23.scratch.hr_exercises.TestBigDecimalStringSort;
import org.junit.runners.Suite;

import com.ping23.scratch.util.TestFileUtilities;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestSuperStack.class, TestBigDecimalStringSort.class,
    TestFileUtilities.class })
public class TestSuite {
    // nothing
}
