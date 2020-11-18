package com.project.segunfrancis.local

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest

/**
 * Created by SegunFrancis
 *
 * Convenience method for calling [runBlockingTest] on an existing [TestCoroutineDispatcher].
 */

@ExperimentalCoroutinesApi
fun MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
    this.testDispatcher.runBlockingTest {
        block()
    }