package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomSetTest {

    @Test
    void add_returnsTrueForNewElement() {
        CustomSet<String> set = new CustomSet<>();
        assertTrue(set.add("L001"));
        assertEquals(1, set.size());
    }

    @Test
    void add_returnsFalseForDuplicateElement() {
        CustomSet<String> set = new CustomSet<>();
        set.add("L001");
        assertFalse(set.add("L001"));
        assertEquals(1, set.size()); // no duplicate stored
    }

    @Test
    void contains_reflectsMembership() {
        CustomSet<String> set = new CustomSet<>();
        set.add("L001");
        assertTrue(set.contains("L001"));
        assertFalse(set.contains("L999"));
    }

    @Test
    void remove_deletesMemberAndUpdatesSize() {
        CustomSet<String> set = new CustomSet<>();
        set.add("L001");
        assertTrue(set.remove("L001"));
        assertFalse(set.contains("L001"));
        assertEquals(0, set.size());
    }

    @Test
    void edgeCase_emptySet() {
        CustomSet<String> set = new CustomSet<>();
        assertTrue(set.isEmpty());
        assertFalse(set.remove("anything"));
    }
}
