package com.ping23.scratch;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Algorithm for moving an item (changing index) within in a consecutive sequence
 * @author jls
 *
 */
public class MoveItemInConsecutiveSequence {

    private static final Logger LOG = LoggerFactory.getLogger(MoveItemInConsecutiveSequence.class);

    private static final Random random = new Random();

    @Test
    public void testGetUpdatedIndex() {

        Integer valueToTest = 0;
        Integer originalPosition = 0;
        Integer newPosition = 0;
        Integer siblingPosition = 0;
        Integer expectedReturnValue = 0;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        for (int index = 0; index < 1000; ++index) {

            LOG.info("iteration: " + index);

            originalPosition = random.nextInt(100);
            newPosition = random.nextInt(100);
            int range = Math.abs(originalPosition - newPosition);

            // default original < new
            Integer delta = 1;
            Integer lowerBound = newPosition;
            Integer upperBound = originalPosition;

            if (originalPosition < newPosition) {
                lowerBound = originalPosition;
                upperBound = newPosition;
            }

            LOG.info("originalPosition: " + originalPosition);
            LOG.info("newPosition: " + newPosition);
            LOG.info("range: " + range);
            LOG.info("lowerBound: " + lowerBound);
            LOG.info("upperBound: " + upperBound);

            // strictly below range
            if (lowerBound > 0) {
                siblingPosition = random.nextInt(lowerBound);
                expectedReturnValue = siblingPosition;
                LOG.info("siblingPosition: " + siblingPosition);
                LOG.info("delta: " + delta);
                LOG.info("expectedReturnValue: " + expectedReturnValue);
                valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
                assertEquals(expectedReturnValue, valueToTest);
                LOG.info("BELOW RANGE TEST PASSED");
            } else {
                // in this case there's no such thing as "below range"
            }

            // within range, inclusive of both bounds
            siblingPosition = random.nextInt(range + 1) + lowerBound;

            if (siblingPosition.equals(originalPosition)) {
                expectedReturnValue = newPosition;
            } else {
                if (originalPosition > newPosition) {
                    delta = 1;
                } else {
                    delta = -1;
                }
                LOG.info("delta: " + delta);
                expectedReturnValue = siblingPosition + delta;
            }
            LOG.info("siblingPosition: " + siblingPosition);
            LOG.info("expectedReturnValue: " + expectedReturnValue);
            valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
            assertEquals(expectedReturnValue, valueToTest);
            LOG.info("WITHIN RANGE TEST PASSED");

            // strictly above range
            siblingPosition = random.nextInt(100) + upperBound + 1;
            delta = 0;
            expectedReturnValue = siblingPosition + delta;
            LOG.info("siblingPosition: " + siblingPosition);
            LOG.info("delta: " + delta);
            LOG.info("expectedReturnValue: " + expectedReturnValue);
            valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
            assertEquals(expectedReturnValue, valueToTest);
            LOG.info("ABOVE RANGE TEST PASSED");
        }

        // boundary cases ===========================================================

        originalPosition = 4;
        newPosition = 4;

        siblingPosition = 4;
        expectedReturnValue = 4;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 0;
        expectedReturnValue = 0;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        originalPosition = 0;
        newPosition = 0;

        siblingPosition = 4;
        expectedReturnValue = 4;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 0;
        expectedReturnValue = 0;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        // new = orig ===============================================================

        originalPosition = 4;
        newPosition = 4;

        siblingPosition = 1;
        expectedReturnValue = 1;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 4;
        expectedReturnValue = 4;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        // new < orig ===============================================================

        originalPosition = 4;
        newPosition = 2;

        // siblingPosition = originalPosition
        siblingPosition = originalPosition;
        expectedReturnValue = newPosition;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 1;
        expectedReturnValue = 1;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 2;
        expectedReturnValue = 3;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 3;
        expectedReturnValue = 4;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 6;
        expectedReturnValue = 6;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        // new > orig ================================================================

        originalPosition = 2;
        newPosition = 4;

        // siblingPosition = originalPosition
        siblingPosition = originalPosition;
        expectedReturnValue = newPosition;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 1;
        expectedReturnValue = 1;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 4;
        expectedReturnValue = 3;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 3;
        expectedReturnValue = 2;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

        siblingPosition = 6;
        expectedReturnValue = 6;
        valueToTest = getUpdatedIndex(siblingPosition, originalPosition, newPosition);
        assertEquals(expectedReturnValue, valueToTest);

    }

    /**
     * get the updated index for a sibling item in the list
     * note that the "sibling in question" may or may not be the sibling to be moved
     * @param siblingPosition is the current index of the sibling in question
     * @param originalPosition is the starting position of the sibling to be moved
     * @param newPosition is the new position of the sibling to be moved
     * @return
     */
    private static Integer getUpdatedIndex(final Integer siblingPosition, final Integer originalPosition,
            final Integer newPosition) {

        Integer returnValue = siblingPosition;

        // sibling = target
        if (siblingPosition.equals(originalPosition)) {
            returnValue = newPosition;
        }

        else if (newPosition < originalPosition) {
            // rule: if s >= new && s < orig then s += 1
            if (siblingPosition >= newPosition && siblingPosition < originalPosition) {
                returnValue = siblingPosition + 1;
            }
        }

        else if (newPosition > originalPosition) {
            // rule: if s <= new && s > orig then s -= 1
            if (siblingPosition <= newPosition && siblingPosition > originalPosition) {
                returnValue = siblingPosition - 1;
            }
        }
        return returnValue;
    }

}
