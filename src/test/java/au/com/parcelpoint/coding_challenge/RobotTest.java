package au.com.parcelpoint.coding_challenge;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RobotTest {

    private Robot robot;

    @Before
    public void setUp() {
        this.robot = new Robot(5, 10);

    }

    @Test
    public void testForwardDropOnly() {
        Map<String, List<String>> result = robot.simulateArm("FD{foo}");
        assertEquals(1, result.get("A").size());
        assertEquals("foo", result.get("A").get(0));
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());
    }

    @Test
    public void testDropOnly() {
        Map<String, List<String>> result = robot.simulateArm("D{foo}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());
    }

    @Test
    public void testForwardDropDrop() {
        Map<String, List<String>> result = robot.simulateArm("FD{a}D{b}");
        assertEquals(1, result.get("A").size());
        assertEquals("a", result.get("A").get(0));
        assertEquals(1, result.get("B").size());
        assertEquals("b", result.get("B").get(0));
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());
    }

    @Test
    public void testForwardDropLastColumn() {
        Map<String, List<String>> result = robot.simulateArm("FFFFFD{x}FD{y}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(2, result.get("E").size());
        assertEquals("x", result.get("E").get(0));
        assertEquals("y", result.get("E").get(1));
    }

    @Test
    public void testForwardDropReturn() {
        Map<String, List<String>> result = robot.simulateArm("FRFRD{i}D{j}");
        assertEquals(1, result.get("A").size());
        assertEquals("j", result.get("A").get(0));
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());
    }

    @Test
    public void testDropLessThanTen() {
        Map<String, List<String>> result = robot.simulateArm("FFFD{a}D{b}D{c}D{d}D{e}D{f}D{g}D{h}D{i}D{j}D{k}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(1, result.get("C").size());
        assertEquals("a", result.get("C").get(0));
        assertEquals(1, result.get("D").size());
        assertEquals("b", result.get("D").get(0));
        assertEquals(9, result.get("E").size());
        assertEquals("c", result.get("E").get(0));
        assertEquals("d", result.get("E").get(1));
        assertEquals("e", result.get("E").get(2));
        assertEquals("f", result.get("E").get(3));
        assertEquals("g", result.get("E").get(4));
        assertEquals("h", result.get("E").get(5));
        assertEquals("i", result.get("E").get(6));
        assertEquals("j", result.get("E").get(7));
        assertEquals("k", result.get("E").get(8));
    }

    @Test
    public void testDropMoreThanTen() {
        Map<String, List<String>> result = robot.simulateArm("FFFD{a}D{b}D{c}D{d}D{e}D{f}D{g}D{h}D{i}D{j}D{k}D{l}D{m}D{n}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(1, result.get("C").size());
        assertEquals("a", result.get("C").get(0));
        assertEquals(1, result.get("D").size());
        assertEquals("b", result.get("D").get(0));
        assertEquals(10, result.get("E").size());
        assertEquals("c", result.get("E").get(0));
        assertEquals("d", result.get("E").get(1));
        assertEquals("e", result.get("E").get(2));
        assertEquals("f", result.get("E").get(3));
        assertEquals("g", result.get("E").get(4));
        assertEquals("h", result.get("E").get(5));
        assertEquals("i", result.get("E").get(6));
        assertEquals("j", result.get("E").get(7));
        assertEquals("k", result.get("E").get(8));
        assertEquals("l", result.get("E").get(9));
    }

    @Test
    public void testDropMoreThanTenInLastColumn() {
        Map<String, List<String>> result = robot.simulateArm("FFFFFD{a}D{b}D{c}D{d}D{e}D{f}D{g}D{h}D{i}D{j}D{k}D{l}D{m}D{n}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(10, result.get("E").size());
        assertEquals("a", result.get("E").get(0));
        assertEquals("b", result.get("E").get(1));
        assertEquals("c", result.get("E").get(2));
        assertEquals("d", result.get("E").get(3));
        assertEquals("e", result.get("E").get(4));
        assertEquals("f", result.get("E").get(5));
        assertEquals("g", result.get("E").get(6));
        assertEquals("h", result.get("E").get(7));
        assertEquals("i", result.get("E").get(8));
        assertEquals("j", result.get("E").get(9));
    }

    @Test
    public void testGivenExample() {
        Map<String, List<String>> result = robot.simulateArm("FD{foo}FFD{bar}RFFD{Foo}RFFD{World}D{}DA{hello}");
        assertEquals(1, result.get("A").size());
        assertEquals("foo", result.get("A").get(0));
        assertEquals(2, result.get("B").size());
        assertEquals("Foo", result.get("B").get(0));
        assertEquals("World", result.get("B").get(1));
        assertEquals(1, result.get("C").size());
        assertEquals("", result.get("C").get(0));
        assertEquals(2, result.get("D").size());
        assertEquals("bar", result.get("D").get(0));
        assertEquals("hello", result.get("D").get(1));
        assertEquals(0, result.get("E").size());
    }

    @Test
    public void testIgnoreOtherCommand() {
        Map<String, List<String>> result = robot.simulateArm("FADaaaaaaaaaa{a}");
        assertEquals(1, result.get("A").size());
        assertEquals("a", result.get("A").get(0));
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());

    }

    @Test
    public void testDropIgnoreOtherCommand() {
        Map<String, List<String>> result = robot.simulateArm("FFFDAAAA{a}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(1, result.get("C").size());
        assertEquals("a", result.get("C").get(0));
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());
    }

    @Test
    public void testCommandCaseSensitive() {
        Map<String, List<String>> result = robot.simulateArm("frd{x}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());
    }

    @Test
    public void testNoStringDrop() {
        Map<String, List<String>> result = robot.simulateArm("FDR");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());

    }

    @Test
    public void testMissingParenthesis() {
        Map<String, List<String>> result = robot.simulateArm("FD{FD");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());

    }

    @Test
    public void testNoValidCommand() {
        Map<String, List<String>> result = robot.simulateArm("{A}");
        assertEquals(0, result.get("A").size());
        assertEquals(0, result.get("B").size());
        assertEquals(0, result.get("C").size());
        assertEquals(0, result.get("D").size());
        assertEquals(0, result.get("E").size());
    }
}
